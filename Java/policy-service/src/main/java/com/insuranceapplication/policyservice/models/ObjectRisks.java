package com.insuranceapplication.policyservice.models;


public class ObjectRisks {
    private Integer id;
    private String riskId;
    private Integer objectNo;
    private Integer premium;
    private Integer premiumForPeriod;
    private boolean isSelected;

    public ObjectRisks() {
    }

    public ObjectRisks(Integer id, String riskId, Integer objectNo, Integer premium, Integer premiumForPeriod, boolean isSelected) {
        this.id = id;
        this.riskId = riskId;
        this.objectNo = objectNo;
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

    public Integer getObjectNo() {
        return objectNo;
    }

    public void setObjectNo(Integer objectNo) {
        this.objectNo = objectNo;
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
