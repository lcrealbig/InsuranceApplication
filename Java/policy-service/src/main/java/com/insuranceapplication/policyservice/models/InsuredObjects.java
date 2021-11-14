package com.insuranceapplication.policyservice.models;

import java.util.Date;

public class InsuredObjects {
    private int objectId;
    private int policyLineId;
    private int transactionId;
    private String type;
    private String c01;
    private String c02;
    private String c03;
    private String c04;
    private int n01;
    private int n02;
    private int n03;
    private int n04;
    private int n05;
    private int n06;
    private int n07;
    private Date d01;
    private Date d02;
    private Date d03;

    public InsuredObjects() {
    }

    public InsuredObjects(int objectId, int policyLineId, int transactionId, String type, String c01, String c02, String c03, String c04, int n01, int n02, int n03, int n04, int n05, int n06, int n07, Date d01, Date d02, Date d03, String version) {
        this.objectId = objectId;
        this.policyLineId = policyLineId;
        this.transactionId = transactionId;
        this.type = type;
        this.c01 = c01;
        this.c02 = c02;
        this.c03 = c03;
        this.c04 = c04;
        this.n01 = n01;
        this.n02 = n02;
        this.n03 = n03;
        this.n04 = n04;
        this.n05 = n05;
        this.n06 = n06;
        this.n07 = n07;
        this.d01 = d01;
        this.d02 = d02;
        this.d03 = d03;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public int getPolicyLineId() {
        return policyLineId;
    }

    public void setPolicyLineId(int policyLineId) {
        this.policyLineId = policyLineId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getC01() {
        return c01;
    }

    public void setC01(String c01) {
        this.c01 = c01;
    }

    public String getC02() {
        return c02;
    }

    public void setC02(String c02) {
        this.c02 = c02;
    }

    public String getC03() {
        return c03;
    }

    public void setC03(String c03) {
        this.c03 = c03;
    }

    public String getC04() {
        return c04;
    }

    public void setC04(String c04) {
        this.c04 = c04;
    }

    public Integer getN01() {
        return n01;
    }

    public void setN01(Integer n01) {
        this.n01 = n01;
    }

    public Integer getN02() {
        return n02;
    }

    public void setN02(Integer n02) {
        this.n02 = n02;
    }

    public Integer getN03() {
        return n03;
    }

    public void setN03(Integer n03) {
        this.n03 = n03;
    }

    public Integer getN04() {
        return n04;
    }

    public void setN04(Integer n04) {
        this.n04 = n04;
    }

    public int getN05() {
        return n05;
    }

    public void setN05(int n05) {
        this.n05 = n05;
    }

    public int getN06() {
        return n06;
    }

    public void setN06(int n06) {
        this.n06 = n06;
    }

    public int getN07() {
        return n07;
    }

    public void setN07(int n07) {
        this.n07 = n07;
    }

    public Date getD01() {
        return d01;
    }

    public void setD01(Date d01) {
        this.d01 = d01;
    }

    public Date getD02() {
        return d02;
    }

    public void setD02(Date d02) {
        this.d02 = d02;
    }

    public Date getD03() {
        return d03;
    }

    public void setD03(Date d03) {
        this.d03 = d03;
    }
}
