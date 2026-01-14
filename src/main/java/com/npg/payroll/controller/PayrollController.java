package com.npg.payroll.controller;

import com.npg.payroll.entity.Payroll;
import com.npg.payroll.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payroll")
public class PayrollController {
    private final PayrollService payrollService;

    @PostMapping()
    public String calculate() throws Exception{
        String status = payrollService.calculate();
        return status;
    }

    @GetMapping()
    public List<Payroll> getPayroll() throws  Exception{
        List<Payroll> payrollList = payrollService.getAllPayroll();
        return  payrollList;
    }

    @GetMapping("/{month}")
    public List<Payroll> getPayrollByMonth(String month) throws Exception{
        List<Payroll> payrollList = payrollService.getPayrollByMonth(month);
        return payrollList;
    }

    @GetMapping("/employeeId/{employeeId}")
    public List<Payroll> getPayrollByEmployeeId(Long id) throws  Exception{
        List<Payroll> payrollList = payrollService.getPayrollByEmployeeId(id);
        return  payrollList;
    }

    @GetMapping("/employeeId/{employeeId}/{month}")
    public Payroll getPayrollByEmployeeIdAndMonth(Long id,String month) throws  Exception{
        Payroll payroll = payrollService.getPayrollEmployeeByMonth(id,month);
        return  payroll;
    }


}
