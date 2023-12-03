package com.bezkoder.spring.datajpa.repository;

import com.bezkoder.spring.datajpa.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

    List<Leave> findByEmployeeId(Long employeeId);
    List<Leave> findByEmployeeIdAndStartDateBetween(Long employeeId, Date startDate, Date endDate);
}
