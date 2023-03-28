package com.PBL3.utils.mapper;

import com.PBL3.models.Business;

import java.sql.ResultSet;

public class BusinessMapper implements IMapper<Business> {

    @Override
    public Business mapRow(ResultSet result) {
        // TODO Auto-generated method stub
        Business business = new Business();
        try {
            business.setId(result.getString("business_id"));
            business.setBusinessName(result.getString("business_name"));
            business.setModifiedBy(result.getString("modified_by"));
            business.setUpdatedAt(result.getTimestamp("updated_at"));
            business.setCreatedAt(result.getTimestamp("created_at"));

            return business;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return null;
    }

}
