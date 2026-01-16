package com.npg.payroll.repository;

import com.npg.payroll.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate,Long> {

    List<ExchangeRate> findByExchangeRateFileId(Long id);

    @Modifying
    @Query("""
            UPDATE ExchangeRate e 
            set e.recordStatus = 'D'
            where e.exchangeRateFileId = :fileId
            """)
    void updateAllExchangeRateByFileID(@Param("fileId") Long id);

    @Query("""
            select e from ExchangeRate e where recordStatus = 'A'
            """)
    List<ExchangeRate> findAllExchangeRateRecordA();

}
