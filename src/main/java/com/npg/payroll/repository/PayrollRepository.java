package com.npg.payroll.repository;

import com.npg.payroll.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll,Long> {

    List<Payroll> findByMonth(String month);

    List<Payroll> findByEmployeeId(Long id);

    Payroll findByEmployeeIdAndMonth(Long id,String month);
}
