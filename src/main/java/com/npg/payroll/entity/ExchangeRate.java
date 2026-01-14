package com.npg.payroll.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="tb_exchange_rate")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //THB
    private String fromCurrency;

    //USD,...
    private String toCurrency;

    //วันที่ระบุอัตราแลกเปลี่ยน
    private String date;

    //อัตราเเลกเปลี่ยน
    @Column(precision = 15,scale = 2)
    private BigDecimal rate;
}
