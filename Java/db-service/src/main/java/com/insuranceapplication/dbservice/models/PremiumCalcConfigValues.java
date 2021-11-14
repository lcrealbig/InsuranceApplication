package com.insuranceapplication.dbservice.models;
import javax.persistence.*;

@Entity
@Table(name = "premium_calc_config_values")
public class PremiumCalcConfigValues {
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
    @Column(name = "value_1")
    private String value1;
    @Column(name = "value_2")
    private String value2;
    @Column(name = "value_3")
    private String value3;
    @Column(name = "value_4")
    private String value4;
    @Column(name = "value_5")
    private String value5;
    @Column(name = "value_6")
    private String value6;
    @Column(name = "value_7")
    private String value7;
    @Column(name = "value_8")
    private String value8;
    @Column(name = "value_9")
    private String value9;
    @Column(name = "value_10")
    private String value10;
    @Column(name = "value_11")
    private String value11;
    @Column(name = "value_12")
    private String value12;
    @Column(name = "version")
    private String version;

    public PremiumCalcConfigValues() {
    }

    public PremiumCalcConfigValues(String comboId, Integer id, String riskId, String combinationName, String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12, String version)
    {
        this.id = id;
        this.riskId = riskId;
        this.comboId = comboId;
        this.combinationName = combinationName;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
        this.value6 = value6;
        this.value7 = value7;
        this.value8 = value8;
        this.value9 = value9;
        this.value10 = value10;
        this.value11 = value11;
        this.value12 = value12;
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

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getValue4() {
        return value4;
    }

    public void setValue4(String value4) {
        this.value4 = value4;
    }

    public String getValue5() {
        return value5;
    }

    public void setValue5(String value5) {
        this.value5 = value5;
    }

    public String getValue6() {
        return value6;
    }

    public void setValue6(String value6) {
        this.value6 = value6;
    }

    public String getValue7() {
        return value7;
    }

    public void setValue7(String value7) {
        this.value7 = value7;
    }

    public String getValue8() {
        return value8;
    }

    public void setValue8(String value8) {
        this.value8 = value8;
    }

    public String getValue9() {
        return value9;
    }

    public void setValue9(String value9) {
        this.value9 = value9;
    }

    public String getValue10() {
        return value10;
    }

    public void setValue10(String value10) {
        this.value10 = value10;
    }

    public String getValue11() {
        return value11;
    }

    public void setValue11(String value11) {
        this.value11 = value11;
    }

    public String getValue12() {
        return value12;
    }

    public void setValue12(String value12) {
        this.value12 = value12;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
