package com.PBL3.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessTypes extends AbstractModel {

    private String businessId;
    private String typeName;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String bussinessId) {
        this.businessId = bussinessId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
