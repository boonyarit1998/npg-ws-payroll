package com.npg.payroll.repository;

import com.npg.payroll.entity.ExchangeRate;
import com.npg.payroll.entity.ExchangeRateFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRateFileRepository extends JpaRepository<ExchangeRateFile,Long> {

    //ตัวอย่างการใช้  nativeQuery false
    @Query("""
            select e from ExchangeRateFile e where recordStatus = 'A' and exchangeRateFileDate = :date
            """)
    ExchangeRateFile fileExchangeRateFileDateRecordA(@Param("date") String date);

    //ตัวอย่างการใช้  nativeQuery ture
    @Query(value = """
            select * from tb_exchange_rate_file  where record_status = 'A'
            """,nativeQuery = true)
    List<ExchangeRateFile> findAllExchangeRateFileRecordA();
}
