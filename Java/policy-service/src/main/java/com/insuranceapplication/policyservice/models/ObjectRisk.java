package com.insuranceapplication.policyservice.models;



public class ObjectRisk {
    private Integer id;
    private String riskId;
    private Integer objectId;
    private Double premium;
    private Integer depositAmount;
    private Integer premiumForPeriod;
    private String isSelected;

    public ObjectRisk() {
    }

    public ObjectRisk(Integer id, String riskId, Integer objectId, Double premium, Integer depositAmount, Integer premiumForPeriod, String isSelected) {
        this.id = id;
        this.riskId = riskId;
        this.objectId = objectId;
        this.premium = premium;
        this.depositAmount = depositAmount;
        this.premiumForPeriod = premiumForPeriod;
        this.isSelected = isSelected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }

    public Integer getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Integer depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Integer getPremiumForPeriod() {
        return premiumForPeriod;
    }

    public void setPremiumForPeriod(Integer premiumForPeriod) {
        this.premiumForPeriod = premiumForPeriod;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }
}
