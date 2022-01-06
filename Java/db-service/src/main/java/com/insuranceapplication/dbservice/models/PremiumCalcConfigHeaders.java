package com.insuranceapplication.dbservice.models;
import javax.persistence.*;

@Entity
@Table(name = "premium_calc_config_headers")
public class PremiumCalcConfigHeaders {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RISKS_VALUES")
    @SequenceGenerator(name = "RISKS_VALUES", allocationSize = 1)
    private Integer id;
    @Column(name = "risk_id")
    private String riskId;
    @Column(name = "combo_id")
    private String comboId;
    @Column(name = "combination_name")
    private String combinationName;
    @Column(name = "header_1")
    private String header1;
    @Column(name = "header_2")
    private String header2;
    @Column(name = "header_3")
    private String header3;
    @Column(name = "header_4")
    private String header4;
    @Column(name = "header_5")
    private String header5;
    @Column(name = "header_6")
    private String header6;
    @Column(name = "header_7")
    private String header7;
    @Column(name = "header_8")
    private String header8;
    @Column(name = "header_9")
    private String header9;
    @Column(name = "header_10")
    private String header10;
    @Column(name = "header_11")
    private String header11;
    @Column(name = "header_12")
    private String header12;
    @Column(name = "version")
    private String version;

    public PremiumCalcConfigHeaders() {
    }

    public PremiumCalcConfigHeaders(String comboId, Integer id, String riskId, String combinationName, String header1, String header2, String header3, String header4, String header5, String header6, String header7, String header8, String header9, String header10, String header11, String header12, String version)
    {
        this.id = id;
        this.riskId = riskId;
        this.comboId = comboId;
        this.combinationName = combinationName;
        this.header1 = header1;
        this.header2 = header2;
        this.header3 = header3;
        this.header4 = header4;
        this.header5 = header5;
        this.header6 = header6;
        this.header7 = header7;
        this.header8 = header8;
        this.header9 = header9;
        this.header10 = header10;
        this.header11 = header11;
        this.header12 = header12;
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
        return header1;
    }

    public void setHeader1(String header1) {
        this.header1 = header1;
    }

    public String getHeader2() {
        return header2;
    }

    public void setHeader2(String header2) {
        this.header2 = header2;
    }

    public String getHeader3() {
        return header3;
    }

    public void setHeader3(String header3) {
        this.header3 = header3;
    }

    public String getHeader4() {
        return header4;
    }

    public void setHeader4(String header4) {
        this.header4 = header4;
    }

    public String getHeader5() {
        return header5;
    }

    public void setHeader5(String header5) {
        this.header5 = header5;
    }

    public String getHeader6() {
        return header6;
    }

    public void setHeader6(String header6) {
        this.header6 = header6;
    }

    public String getHeader7() {
        return header7;
    }

    public void setHeader7(String header7) {
        this.header7 = header7;
    }

    public String getHeader8() {
        return header8;
    }

    public void setHeader8(String header8) {
        this.header8 = header8;
    }

    public String getHeader9() {
        return header9;
    }

    public void setHeader9(String header9) {
        this.header9 = header9;
    }

    public String getHeader10() {
        return header10;
    }

    public void setHeader10(String header10) {
        this.header10 = header10;
    }

    public String getHeader11() {
        return header11;
    }

    public void setHeader11(String header11) {
        this.header11 = header11;
    }

    public String getHeader12() {
        return header12;
    }

    public void setHeader12(String header12) {
        this.header12 = header12;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
