package com.PBL3.utils.mapper;

import com.PBL3.models.PlanModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanMapper implements IMapper<PlanModel> {
    @Override
    public PlanModel mapRow(ResultSet result) {
        PlanModel plan = new PlanModel();
        try {
            plan.setId(result.getString("plan_id"));
            plan.setCompanyId(result.getString("company_id"));
            plan.setAction(result.getInt("action"));
            plan.setTime(result.getTimestamp("time"));
            plan.setPath(result.getString("path"));
            plan.setModifiedBy(result.getString("modified_by"));
            plan.setCreatedAt(result.getTimestamp("create_at"));
            plan.setUpdatedAt(result.getTimestamp("update_at"));
            return  plan;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
