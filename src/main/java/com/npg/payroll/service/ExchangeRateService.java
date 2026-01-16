package com.npg.payroll.service;

import com.npg.payroll.entity.ExchangeRate;
import com.npg.payroll.entity.ExchangeRateFile;
import com.npg.payroll.exception.UploadFileDuplicateException;
import com.npg.payroll.exception.UploadFileException;
import com.npg.payroll.repository.ExchangeRateFileRepository;
import com.npg.payroll.repository.ExchangeRateRepository;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    private final ExchangeRateFileRepository exchangeRateFileRepository;

    @Value("${exchange_rate_upload.prefix.filename}")
    private String prefixFileName;

    @Transactional
    public List<ExchangeRate> uploadExchangeRate(String filename,String base64Data) throws Exception {
        LocalDateTime currenDateTime = LocalDateTime.now();
        String dateFile = getStringFromFileName(filename);

        //check ชื่อไฟลล์
        if(!vaildateExchangeRateFileName(filename)){
            throw new UploadFileException("upload file error");
        }

        ExchangeRateFile Duplicate = exchangeRateFileRepository.exchangeRateFileDateRecordA(dateFile);

        
        //เช็คไฟลล์ซ้ำ
        if (Duplicate != null){

            exchangeRateRepository.updateAllExchangeRateByFileID(Duplicate.getId());

            Duplicate.setRecordStatus("D");
            Duplicate.setUpdateBy("SYSTEM");
            Duplicate.setUpdateDate(currenDateTime);
            exchangeRateFileRepository.save(Duplicate);
        }

        //กรณีไม่ซ้ำเพิ่มใหม่
        ExchangeRateFile exchangeRateFile = ExchangeRateFile.builder()
                .exchangeRateFileName(filename)
                .exchangeRateFileDate(dateFile)
                .uploadBy("SYSTEM")
                .uploadDate(currenDateTime)
                .uploadStatus("P")
                .recordStatus("A")
                .updateDate(currenDateTime)
                .updateBy("SYSTEM")
                .build();

        ExchangeRateFile newExchangeRateFile = exchangeRateFileRepository.save(exchangeRateFile);

        List<ExchangeRate> tempRows = new ArrayList<>();

        int startRow = 3;
        int maxRow = 22;

        byte[] decodeBytes = DatatypeConverter.parseBase64Binary(base64Data);
        try(InputStream inputStream = new ByteArrayInputStream(decodeBytes);Workbook workbook = new XSSFWorkbook(inputStream)){
            Sheet sheet = workbook.getSheetAt(0);
            for(Row row : sheet){
                if(row.getRowNum() > maxRow){
                    break;
                }
                if (row.getRowNum() > startRow){
                    ExchangeRate exchangeRate = ExchangeRate.builder()
                            .exchangeRateFileId(newExchangeRateFile.getId())
                            .recordStatus("A")
                            .currencyCode(row.getCell(1).getStringCellValue())
                            .buyingSightBill(getBigDecimalCellValue(row.getCell(2)))
                            .buyingTt(getBigDecimalCellValue(row.getCell(3)))
                            .sellingBillDdTt(getBigDecimalCellValue(row.getCell(4)))
                            .build();
                    tempRows.add(exchangeRate);
                }
            }
            exchangeRateRepository.saveAll(tempRows);
        } catch (Exception e) {
            log.error("Error upload");
            throw e;
        }

        return tempRows;
    }

    public List<ExchangeRate> getAllExchangeRate() throws  Exception {
        List<ExchangeRate> exchangeRateList = exchangeRateRepository.findAllExchangeRateRecordA();
        return exchangeRateList;
    }

    public List<ExchangeRate> getExchangRateByDate(String date) throws  Exception {
        ExchangeRateFile exchangeRateFile = exchangeRateFileRepository.exchangeRateFileDateRecordA(date);
        List<ExchangeRate> exchangeRate = exchangeRateRepository.findByExchangeRateFileId(exchangeRateFile.getId());
        return exchangeRate;
    }

    public void deleteExchangRate(String date) throws Exception{
        //หาไฟล์ Exchange ตาม date
        ExchangeRateFile exchangeRateFile = exchangeRateFileRepository.exchangeRateFileDateRecordA(date);
        //หาไม่เจอ return
        if(exchangeRateFile == null){
            return;
        }
        //เตรียม id สำหรับลบข้อมูล
        List<Long> ids= exchangeRateRepository.findByExchangeRateFileId(exchangeRateFile.getId()).stream().map(ExchangeRate::getId).toList();
        if(!ids.isEmpty()){
            exchangeRateRepository.deleteAllById(ids);
        }
        //ลบ ExchangeRateFile
        exchangeRateFileRepository.deleteById(exchangeRateFile.getId());
    }

    public String getStringFromFileName(String filename){
        String date = filename.substring(prefixFileName.length(),prefixFileName.length()+8);
        return date;
    }

    private static BigDecimal getBigDecimalCellValue(Cell cell)throws  Exception{
        if(cell == null){
            return null;
        }
        if(cell.getCellType() == CellType.NUMERIC){
            return  BigDecimal.valueOf((cell.getNumericCellValue()));
        }else if (cell.getCellType() == CellType.STRING){
            try {
                return new BigDecimal(cell.getStringCellValue());
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid amount format");
            }
        }
        return null;
    }

    public boolean vaildateExchangeRateFileName(String filename){
        //ชื่อไฟลล์ห้ามว่าง
        if(StringUtil.isBlank(filename)){
            return false;
        }
        //ชื่อไฟลล์ต้องขึ้นต้น ER_XLSX_
        if (!filename.startsWith(prefixFileName)){
            return false;
        }
        return true;
    }

}
