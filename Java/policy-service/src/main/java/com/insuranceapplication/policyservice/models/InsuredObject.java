package com.insuranceapplication.policyservice.models;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "INSURED_OBJECTS")
public class InsuredObject {
    private int object_id;
    private int policy_line_no;
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

    public InsuredObject(int object_id, int policy_line_no, String type, String c01, String c02, String c03,
                         String c04, int n01, int n02, int n03, int n04, Date d01, Date d02, Date d03) {
        this.object_id = object_id;
        this.policy_line_no = policy_line_no;
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

    @Id
    public int getObject_id() {
        return object_id;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    public int getPolicy_line_no() {
        return policy_line_no;
    }

    public void setPolicy_line_no(int policy_line_no) {
        this.policy_line_no = policy_line_no;
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
