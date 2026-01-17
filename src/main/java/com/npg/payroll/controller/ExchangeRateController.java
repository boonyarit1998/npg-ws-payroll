package com.npg.payroll.controller;

import com.npg.payroll.dto.UploadExchangeRateRequest;
import com.npg.payroll.entity.ExchangeRate;
import com.npg.payroll.entity.ExchangeRateFile;
import com.npg.payroll.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exchange_rate")
@RequiredArgsConstructor
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    @PostMapping()
    public ResponseEntity<List<ExchangeRate>> uploadExchangeRate(@RequestBody UploadExchangeRateRequest request) throws Exception{
        List<ExchangeRate> newExchangRate = exchangeRateService.uploadExchangeRate(request.getFilename(),request.getBase64Data());
        return ResponseEntity.ok().body(newExchangRate);
    }

    @GetMapping()
    public ResponseEntity<List<ExchangeRate>> getAllExchangeRate() throws Exception{
        List<ExchangeRate> exchangeRateList = exchangeRateService.getAllExchangeRate();
        return ResponseEntity.ok().body(exchangeRateList);
    }

    @GetMapping("/file")
    public ResponseEntity<List<ExchangeRateFile>> getAllFileExchangeRate() throws Exception{
        List<ExchangeRateFile> exchangeRateList = exchangeRateService.getAllFileExchangeRate();
        return ResponseEntity.ok().body(exchangeRateList);
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<ExchangeRate>> getExchangeRateByDate(@PathVariable String date) throws Exception{
        List<ExchangeRate> exchangeRate = exchangeRateService.getExchangRateByDate(date);
        return  ResponseEntity.ok().body(exchangeRate);
    }

    @DeleteMapping("/{date}")
    public ResponseEntity<Void> deleteExchangeRate(@PathVariable String date) throws Exception{
        boolean deleteFlag = exchangeRateService.deleteExchangRate(date);
        if (!deleteFlag){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
