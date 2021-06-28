package com.insuranceapplication.policyservice.models;

import javax.persistence.*;

@Entity
@Table(name = "POLICIES")
public class Policy {
    private Integer policy_id;
    private Integer owner_id;
    private String type;
    private String status;
    private String start_date;
    private String end_date;
    private String product_type;
    private String alt_no;

    //no argument constructor is required to create entity class
    public Policy(){
    }

    public Policy(Integer policy_id, Integer owner_id, String type, String status, String start_date, String end_date, String product_type, String alt_no) {
        this.policy_id = policy_id;
        this.owner_id = owner_id;
        this.type = type;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
        this.product_type = product_type;
        this.alt_no = alt_no;
    }

    @Id
    @Column(name = "POLICY_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_id")
    @SequenceGenerator(name = "policy_id", sequenceName = "POLICIES_SEQUENCE", allocationSize = 1)
    public Integer getPolicy_id() {
        return policy_id;
    }

    public void setPolicy_id(Integer policy_id) {
        this.policy_id = policy_id;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getAlt_no() {
        return alt_no;
    }

    public void setAlt_no(String alt_no) {
        this.alt_no = alt_no;
    }
}