package com.PBL3.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Business extends AbstractModel {
    private String businessName;

    /**
     * @return the bussinessName
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * @param bussinessName the bussinessName to set
     */
    public void setBusinessName(String bussinessName) {
        this.businessName = bussinessName;
    }

}
