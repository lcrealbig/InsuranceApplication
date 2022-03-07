package com.insuranceapplication.policyservice.models;


public class Bill {
    private Integer claimId;
    private String billStatus;
    private Double billAmount;
    private Double amountOfWithdraw;
    private Integer victimId;

    public Bill(Integer claimId, String billStatus, Double billAmount, Double amountOfWithdraw, Integer victimId) {
        this.claimId = claimId;
        this.billStatus = billStatus;
        this.billAmount = billAmount;
        this.amountOfWithdraw = amountOfWithdraw;
        this.victimId = victimId;

    }

    public Bill() {
    }

    public Integer getClaimId() {
        return claimId;
    }

    public void setClaimId(Integer claimId) {
        this.claimId = claimId;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public Double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public Double getAmountOfWithdraw() {
        return amountOfWithdraw;
    }

    public void setAmountOfWithdraw(Double amountOfWithdraw) {
        this.amountOfWithdraw = amountOfWithdraw;
    }

    public Integer getVictimId() {
        return victimId;
    }

    public void setVictimId(Integer victimId) {
        this.victimId = victimId;
    }

}
