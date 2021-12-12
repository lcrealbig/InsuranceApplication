package com.insuranceapplication.policyservice.models;


public class ObjectRisks {
    private Integer id;
    private String riskId;
    private Integer objectId;
    private Integer premium;
    private Integer premiumForPeriod;
    private String isSelected;

    public ObjectRisks() {
    }

    public ObjectRisks(Integer id, String riskId, Integer objectNo, Integer premium, Integer premiumForPeriod, String isSelected) {
        this.id = id;
        this.riskId = riskId;
        this.objectId = objectNo;
        this.premium = premium;
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

    public Integer getPremium() {
        return premium;
    }

    public void setPremium(Integer premium) {
        this.premium = premium;
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
