package claimservice.models;

public class Bills {
    private Integer claimId;
    private String billStatus;
    private Double billAmount;
    private Double amountOfWithdraw;
    private Integer victimId;
    private String version;

    public Bills(Integer claimId, String billStatus, Double billAmount, Double amountOfWithdraw, Integer victimId, String version) {
        this.claimId = claimId;
        this.billStatus = billStatus;
        this.billAmount = billAmount;
        this.amountOfWithdraw = amountOfWithdraw;
        this.victimId = victimId;
        this.version = version;
    }

    public Bills() {
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
