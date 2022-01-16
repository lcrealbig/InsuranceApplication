package com.insuranceapplication.dbservice.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Claims")
public class Claims {
    private Integer claimId;
    private Integer policyId;
    private String status;
    private String claimType;
    private String claimDescription;
    private Date claimDate;
    private String lastStatusUpdate;
    private String eventPlace;

    public Claims() {
    }

    public Claims(Integer id,Integer policyId, String status, String claimType, String claimDescription, Date claimDate, String lastStatusUpdate, String eventPlace) {
        this.claimId = id;
        this.policyId = policyId;
        this.status = status;
        this.claimType = claimType;
        this.claimDescription = claimDescription;
        this.claimDate = claimDate;
        this.lastStatusUpdate = lastStatusUpdate;
        this.eventPlace = eventPlace;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "claim_id_seq")
    @SequenceGenerator(name = "claim_id_seq", allocationSize = 1)
    public Integer getClaimId() {
        return claimId;
    }

    public void setClaimId(Integer claimId) {
        this.claimId = claimId;
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

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }
}
