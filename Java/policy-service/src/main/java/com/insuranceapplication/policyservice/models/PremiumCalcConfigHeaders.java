package com.insuranceapplication.policyservice.models;


public class PremiumCalcConfigHeaders {
    private Integer id;
    private String riskId;
    private String comboId;
    private String combinationName;
    private String header_1;
    private String header_2;
    private String header_3;
    private String header_4;
    private String header_5;
    private String header_6;
    private String header_7;
    private String header_8;
    private String header_9;
    private String header_10;
    private String header_11;
    private String header_12;
    private String version;

    public PremiumCalcConfigHeaders() {
    }

    public PremiumCalcConfigHeaders(String comboId, Integer id, String riskId, String combinationName, String header_1, String header_2, String header_3, String header_4, String header_5, String header_6, String header_7, String header_8, String header_9, String header_10, String header_11, String header_12, String version) {
        this.id = id;
        this.riskId = riskId;
        this.comboId = comboId;
        this.combinationName = combinationName;
        this.header_1 = header_1;
        this.header_2 = header_2;
        this.header_3 = header_3;
        this.header_4 = header_4;
        this.header_5 = header_5;
        this.header_6 = header_6;
        this.header_7 = header_7;
        this.header_8 = header_8;
        this.header_9 = header_9;
        this.header_10 = header_10;
        this.header_11 = header_11;
        this.header_12 = header_12;
        this.version = version;
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

    public String getComboId() {
        return comboId;
    }

    public void setComboId(String comboId) {
        this.comboId = comboId;
    }

    public String getCombinationName() {
        return combinationName;
    }

    public void setCombinationName(String combinationName) {
        this.combinationName = combinationName;
    }

    public String getHeader1() {
        return header_1;
    }

    public void setHeader1(String header_1) {
        this.header_1 = header_1;
    }

    public String getHeader2() {
        return header_2;
    }

    public void setHeader2(String header_2) {
        this.header_2 = header_2;
    }

    public String getHeader3() {
        return header_3;
    }

    public void setHeader3(String header_3) {
        this.header_3 = header_3;
    }

    public String getHeader4() {
        return header_4;
    }

    public void setHeader4(String header_4) {
        this.header_4 = header_4;
    }

    public String getHeader5() {
        return header_5;
    }

    public void setHeader5(String header_5) {
        this.header_5 = header_5;
    }

    public String getHeader6() {
        return header_6;
    }

    public void setHeader6(String header_6) {
        this.header_6 = header_6;
    }

    public String getHeader7() {
        return header_7;
    }

    public void setHeader7(String header_7) {
        this.header_7 = header_7;
    }

    public String getHeader8() {
        return header_8;
    }

    public void setHeader8(String header_8) {
        this.header_8 = header_8;
    }

    public String getHeader9() {
        return header_9;
    }

    public void setHeader9(String header_9) {
        this.header_9 = header_9;
    }

    public String getHeader10() {
        return header_10;
    }

    public void setHeader10(String header_10) {
        this.header_10 = header_10;
    }

    public String getHeader11() {
        return header_11;
    }

    public void setHeader11(String header_11) {
        this.header_11 = header_11;
    }

    public String getHeader12() {
        return header_12;
    }

    public void setHeader12(String header_12) {
        this.header_12 = header_12;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
