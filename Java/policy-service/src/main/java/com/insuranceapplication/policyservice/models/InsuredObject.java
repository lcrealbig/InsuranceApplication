package com.insuranceapplication.policyservice.models;

import java.util.Date;

public class InsuredObject {
    private int objectId;
    private int policyLineNo;
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
    private Date d01;
    private Date d02;
    private Date d03;

    //no argument constructor is required to create entity class
    public InsuredObject() {
    }

    public InsuredObject(int objectId, int policyLineNo, int transactionId, String type, String c01, String c02, String c03, String c04, int n01, int n02, int n03, int n04, Date d01, Date d02, Date d03) {
        this.objectId = objectId;
        this.policyLineNo = policyLineNo;
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
        this.d01 = d01;
        this.d02 = d02;
        this.d03 = d03;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getPolicyLineNo() {
        return policyLineNo;
    }

    public void setPolicyLineNo(int policyLineNo) {
        this.policyLineNo = policyLineNo;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
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

    public int getN01() {
        return n01;
    }

    public void setN01(int n01) {
        this.n01 = n01;
    }

    public int getN02() {
        return n02;
    }

    public void setN02(int n02) {
        this.n02 = n02;
    }

    public int getN03() {
        return n03;
    }

    public void setN03(int n03) {
        this.n03 = n03;
    }

    public int getN04() {
        return n04;
    }

    public void setN04(int n04) {
        this.n04 = n04;
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
