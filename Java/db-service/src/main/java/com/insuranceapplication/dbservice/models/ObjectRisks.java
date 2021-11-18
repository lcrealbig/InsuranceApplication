package com.insuranceapplication.dbservice.models;


import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "object_risks")
public class ObjectRisks {
    @Id
    private Integer id;
    @Column(name = "risk_id")
    private String riskId;
    @Column(name = "object_no")
    private Integer objectNo;
    @Column(name = "premium")
    private Integer premium;
    @Column(name = "premium_for_period")
    private Integer premiumForPeriod;
    @Type(type="true_false")
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
