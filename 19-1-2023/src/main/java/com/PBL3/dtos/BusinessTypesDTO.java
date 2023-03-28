package com.PBL3.dtos;

public class BusinessTypesDTO {
    private String businessId;
    private String typeName;
    private String modifiedBy;

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

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

}
