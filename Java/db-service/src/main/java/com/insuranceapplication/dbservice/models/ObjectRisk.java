package com.insuranceapplication.dbservice.models;


import javax.persistence.*;

@Entity
@Table(name = "object_risk")
public class ObjectRisk {
    @Id
    private Integer id;
    @Column(name = "risk_id")
    private String riskId;
    @Column(name = "object_id")
    private Integer objectId;
    @Column(name = "premium")
    private Integer premium;
    @Column(name = "deposit_amount")
    private Integer depositAmount;
    @Column(name = "premium_for_period")
    private Integer premiumForPeriod;
    @Column(name = "is_selected")
    private String isSelected;

    public ObjectRisk() {
    }

    public ObjectRisk(Integer id, String riskId, Integer objectId, Integer premium, Integer depositAmount, Integer premiumForPeriod, String isSelected) {
        this.id = id;
        this.riskId = riskId;
        this.objectId = objectId;
        this.premium = premium;
        this.depositAmount = depositAmount;
        this.premiumForPeriod = premiumForPeriod;
        this.isSelected = isSelected;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "or_id_seq")
    @SequenceGenerator(name = "or_id_seq", allocationSize = 1)
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
