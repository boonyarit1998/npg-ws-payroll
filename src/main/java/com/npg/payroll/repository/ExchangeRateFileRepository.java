package com.npg.payroll.repository;

import com.npg.payroll.entity.ExchangeRate;
import com.npg.payroll.entity.ExchangeRateFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateFileRepository extends JpaRepository<ExchangeRateFile,Long> {

    @Query("""
            select e from ExchangeRateFile e where recordStatus = 'A' and exchangeRateFileDate = :date
            """)
    ExchangeRateFile exchangeRateFileDateRecordA(@Param("date") String date);
}
