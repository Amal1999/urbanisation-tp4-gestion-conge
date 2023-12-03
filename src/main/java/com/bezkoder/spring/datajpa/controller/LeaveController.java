package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.constants.requests.LeaveVerificationRequest;
import com.bezkoder.spring.datajpa.model.Leave;
import com.bezkoder.spring.datajpa.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/leaves")
public class LeaveController {
    @Autowired
    LeaveRepository leaveRepository;

    @PostMapping()
    public ResponseEntity<Leave> createLeaveRequest(@RequestBody Leave leave) {
        try {
            Leave createdLeave = leaveRepository
                    .save(leave);
            return new ResponseEntity<>(createdLeave, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Leave> updateLeaveRequest(@PathVariable("id") long id, @RequestBody Leave leave) {
        try {
            Leave existingLeave = leaveRepository.findById(id).get();

            if (leave.getLeaveType() != null) {
                existingLeave.setLeaveType(leave.getLeaveType());
            }

            if (leave.getStartDate() != null) {
                existingLeave.setStartDate(leave.getStartDate());
            }

            if (leave.getEndDate() != null) {
                existingLeave.setEndDate(leave.getEndDate());
            }

            if (leave.getStatus() != null) {
                existingLeave.setStatus(leave.getStatus());
            }

            return new ResponseEntity<>(leaveRepository.save(existingLeave), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/verify")
    public ResponseEntity<Boolean> verifyLeaveRequest(@RequestBody LeaveVerificationRequest request) {
        if (request.getLeave().getStartDate() == null || request.getLeave().getEndDate() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(request.getLeave().calculateLeaveDays() <= request.getRemainingDays(), HttpStatus.OK);
    }
}
