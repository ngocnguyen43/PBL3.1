package com.PBL3.utils.mapper;

import com.PBL3.models.BusinessTypes;

import java.sql.ResultSet;

public class BusinessTypesMapper implements IMapper<BusinessTypes> {
    private boolean withDate = false;

    public BusinessTypesMapper(boolean withDate) {
        this.withDate = withDate;
    }

    public BusinessTypesMapper() {
    }

    @Override
    public BusinessTypes mapRow(ResultSet result) {
        // TODO Auto-generated method stub
        BusinessTypes businessTypes = new BusinessTypes();
        try {
            businessTypes.setId(result.getString("type_id"));
            businessTypes.setBusinessId(result.getString("business_id"));
            businessTypes.setTypeName(result.getString("type_name"));
            if (withDate) {
                businessTypes.setModifiedBy(result.getString("modified_by"));
                businessTypes.setUpdatedAt(result.getTimestamp("updated_at"));
                businessTypes.setCreatedAt(result.getTimestamp("created_at"));
            }
//            else {
//                businessTypes.setModifiedBy(null);
//                businessTypes.setUpdatedAt(null);
//                businessTypes.setCreatedAt(null);
//            }
            return businessTypes;
//		bussinessTypes.set
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

}
