package com.PBL3.models.pagination;

public class ProductPagination extends AbstractPaginationModel {
    String company;
    String product;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
