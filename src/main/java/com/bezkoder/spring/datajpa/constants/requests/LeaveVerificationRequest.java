package com.bezkoder.spring.datajpa.constants.requests;

import com.bezkoder.spring.datajpa.model.Leave;

public class LeaveVerificationRequest {

    private Leave leave;
    private int remainingDays;

    public Leave getLeave() {
        return leave;
    }

    public void setLeave(Leave leave) {
        this.leave = leave;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }
}

