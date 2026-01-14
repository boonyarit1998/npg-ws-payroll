package com.npg.payroll.service;

import com.npg.payroll.entity.Payroll;
import com.npg.payroll.repository.PayrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayrollService {
    private final PayrollRepository payrollRepository;

    public String calculate()throws  Exception{
        return "calculate";

    }

    public List<Payroll> getAllPayroll()throws  Exception{
        List<Payroll> payroll = payrollRepository.findAll();
        return  payroll;
    }

    public List<Payroll> getPayrollByMonth(String month)throws Exception{
        List<Payroll> payroll = payrollRepository.findByMonth(month);
        return payroll;
    }

    public List<Payroll> getPayrollByEmployeeId(Long id) throws Exception{
        List<Payroll> payroll = payrollRepository.findByEmployeeId(id);
        return payroll;
    }

    public Payroll getPayrollEmployeeByMonth(Long id , String month){
        Payroll payroll = payrollRepository.findByEmployeeIdAndMonth(id,month);
        return payroll;
    }

}
