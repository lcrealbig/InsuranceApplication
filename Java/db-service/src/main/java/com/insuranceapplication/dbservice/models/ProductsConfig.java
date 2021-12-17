package com.insuranceapplication.dbservice.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "PRODUCTS_CONFIG")
public class ProductsConfig {
    private Integer id;
    private String productId;
    private String version;
    private Date startDate;
    private Date endDate;

    public ProductsConfig() {
    }

    public ProductsConfig(int id, String productId, String version, Date startDate, Date endDate) {
        this.id = id;
        this.productId = productId;
        this.version = version;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
