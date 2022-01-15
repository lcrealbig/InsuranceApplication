package claimservice.models;

import java.util.Date;

public class Claims {
    private Integer claimId;
    private String policyLineId;
    private Integer policyId;
    private String status;
    private String claimType;
    private String claimDescription;
    private Date claimDate;
    private String lastStatusUpdate;
    private String version;
    private String eventPlace;
    public Claims() {
    }

    public Claims(Integer id, String policyLineId, Integer policyId, String status, String claimType, String claimDescription, Date claimDate, String lastStatusUpdate, String version, String eventPlace) {
        this.policyLineId = policyLineId;
        this.claimId = id;
        this.policyId = policyId;
        this.status = status;
        this.claimType = claimType;
        this.claimDescription = claimDescription;
        this.claimDate = claimDate;
        this.lastStatusUpdate = lastStatusUpdate;
        this.eventPlace = eventPlace;
        this.version = version;
    }

    public Integer getClaimId() {
        return claimId;
    }

    public void setClaimId(Integer claimId) {
        this.claimId = claimId;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }
}
