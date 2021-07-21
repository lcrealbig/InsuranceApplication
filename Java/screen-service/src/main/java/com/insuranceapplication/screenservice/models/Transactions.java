package com.insuranceapplication.screenservice.models;

import java.util.Date;

public class Transactions {
    private int transaction_id;
    private String modified_by;
    private Date modified_time;
    private String transaction_type;

    public Transactions(int transaction_id, String modified_by, Date modified_time, String transaction_type) {
        this.transaction_id = transaction_id;
        this.modified_by = modified_by;
        this.modified_time = modified_time;
        this.transaction_type = transaction_type;
    }

    public Transactions() {
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getModified_by() {
        return modified_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }

    public Date getModified_time() {
        return modified_time;
    }

    public void setModified_time(Date modified_time) {
        this.modified_time = modified_time;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }
}
