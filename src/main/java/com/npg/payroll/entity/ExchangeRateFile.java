package com.npg.payroll.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name ="tb_exchange_rate_file")
public class ExchangeRateFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String exchangeRateFileDate;

    private LocalDateTime exchangeRateFileDateTime;

    private String exchangeRateFileName;

    private String recordStatus;

    private String uploadStatus;

    private String uploadBy;

    private LocalDateTime uploadDate;

    private String updateBy;

    private LocalDateTime updateDate;
}
