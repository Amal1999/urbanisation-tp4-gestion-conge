package com.bezkoder.spring.datajpa.model;

import com.bezkoder.spring.datajpa.constants.enums.LeaveStatusEnum;
import com.bezkoder.spring.datajpa.constants.enums.LeaveTypeEnum;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "Leave_requests")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private Date startDate;
    private Date endDate;
    private LeaveTypeEnum leaveType;
    private LeaveStatusEnum status;

    public Leave() {
    }

    public Leave(Employee employee, Date startDate, Date endDate, LeaveTypeEnum leaveType, LeaveStatusEnum status) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public LeaveTypeEnum getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveTypeEnum leaveType) {
        this.leaveType = leaveType;
    }

    public LeaveStatusEnum getStatus() {
        return status;
    }

    public void setStatus(LeaveStatusEnum status) {
        this.status = status;
    }


    public long calculateLeaveDays() {
        Instant startInstant = startDate.toInstant();
        Instant endInstant = endDate.toInstant();

        LocalDate startDateLocal = startInstant.atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDateLocal = endInstant.atZone(ZoneId.systemDefault()).toLocalDate();

        return Duration.between(startDateLocal.atStartOfDay(), endDateLocal.atStartOfDay()).toDays() + 1;
    }
}
