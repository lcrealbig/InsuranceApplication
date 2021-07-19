package com.insuranceapplication.policyservice.models;

import javax.persistence.*;

@Entity
@Table(name = "POLICY_LINES")
public class PolicyLine {

    private int line_id;
    private int policy_no;
    private String product_line_type;

    //no argument constructor is required to create entity class
    public PolicyLine(){
    }

    public PolicyLine(int line_id, int policy_no, String product_line_type) {
        this.line_id = line_id;
        this.policy_no = policy_no;
        this.product_line_type = product_line_type;
    }

    @Id
    public int getLine_id() {
        return line_id;
    }

    public void setLine_id(int line_id) {
        this.line_id = line_id;
    }

    public int getPolicy_no() {
        return policy_no;
    }

    public void setPolicy_no(int policy_no) {
        this.policy_no = policy_no;
    }

    public String getProduct_line_type() {
        return product_line_type;
    }

    public void setProduct_line_type(String product_line_type) {
        this.product_line_type = product_line_type;
    }
}
