package com.npg.payroll.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_payroll")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    //รหัสพนักงาน
    private Long employeeId;

    //เงินเดือน
    private BigDecimal salary;

    private String month;

    //สกุลเงินเดือน
    private String currency;

}
