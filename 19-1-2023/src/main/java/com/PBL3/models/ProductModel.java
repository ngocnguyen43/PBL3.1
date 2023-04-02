package com.PBL3.models;

public class ProductModel extends AbstractModel {
    String companyId;
    String productName;

    Integer action;
    String kindof;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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
