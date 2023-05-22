package com.PBL3.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductModel extends AbstractModel {
    String userId;
    String productName;
    String companyName;
    Integer action;
    String kindof;
    @JsonProperty("product_type")
    private KindOfProductModel kindOfProductModel;
    @JsonProperty("certificates")
    private List<Certificate> certificate;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Certificate> getCertificate() {
        return certificate;
    }

    public void setCertificate(List<Certificate> certificate) {
        this.certificate = certificate;
    }

    public KindOfProductModel getKindOfProductModel() {
        return kindOfProductModel;
    }

    public void setKindOfProductModel(KindOfProductModel kindOfProductModel) {
        this.kindOfProductModel = kindOfProductModel;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getKindof() {
        return kindof;
    }

    public void setKindof(String kindof) {
        this.kindof = kindof;
    }
}
