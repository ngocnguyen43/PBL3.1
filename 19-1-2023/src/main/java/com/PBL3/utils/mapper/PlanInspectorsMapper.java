package com.PBL3.utils.mapper;

import com.PBL3.models.PlanInspectorModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanInspectorsMapper implements IMapper<PlanInspectorModel> {
    @Override
    public PlanInspectorModel mapRow(ResultSet result) {
        PlanInspectorModel planInspector = new PlanInspectorModel();
        try {
            planInspector.setId(result.getString("id"));
            planInspector.setPlanId(result.getString("plan_id"));
            planInspector.setUserId(result.getString("user_id"));
            planInspector.setAction(result.getInt("action"));
            planInspector.setModifiedBy(result.getString("modified_by"));
            planInspector.setCreatedAt(result.getTimestamp("created_at"));
            planInspector.setUpdatedAt(result.getTimestamp("updated_at"));
            return planInspector;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
