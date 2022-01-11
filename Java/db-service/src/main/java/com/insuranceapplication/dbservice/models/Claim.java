package com.insuranceapplication.dbservice.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Claim")
public class Claim {
    private Integer id;
    private String policyLineId;
    private Integer policyId;
    private String status;
    private String claimType;
    private String claimDescription;
    private Date claimDate;
    private String lastStatusUpdate;

    public Claim() {
    }

    public Claim(Integer id, String policyLineId, Integer policyId, String status, String claimType, String claimDescription, Date claimDate, String lastStatusUpdate) {
        this.policyLineId = policyLineId;
        this.id = id;
        this.policyId = policyId;
        this.status = status;
        this.claimType = claimType;
        this.claimDescription = claimDescription;
        this.claimDate = claimDate;
        this.lastStatusUpdate = lastStatusUpdate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "claim_id_seq")
    @SequenceGenerator(name = "claim_id_seq", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPolicyLineId() {
        return policyLineId;
    }

    public void setPolicyLineId(String policyLineId) {
        this.policyLineId = policyLineId;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public String getClaimDescription() {
        return claimDescription;
    }

    public void setClaimDescription(String claimDescription) {
        this.claimDescription = claimDescription;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public String getLastStatusUpdate() {
        return lastStatusUpdate;
    }

    public void setLastStatusUpdate(String lastStatusUpdate) {
        this.lastStatusUpdate = lastStatusUpdate;
    }
}
