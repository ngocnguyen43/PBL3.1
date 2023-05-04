package com.PBL3.utils.mapper;

import com.PBL3.models.PlanInspectorModel;
import com.PBL3.models.PlanModel;
import com.PBL3.models.User;
import com.PBL3.utils.helpers.CheckFieldExist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanMapper implements IMapper<PlanModel> {
    @Override
    public PlanModel mapRow(ResultSet result) {
        PlanModel plan = new PlanModel();
        try {
            plan.setId(result.getString("plan_id"));
//            plan.setCompanyId(result.getString("company_id"));
            plan.setAction(result.getInt("action"));
            plan.setTime(result.getTimestamp("time"));
            plan.setPath(result.getString("path"));
            plan.setModifiedBy(result.getString("modified_by"));
            plan.setCreatedAt(result.getTimestamp("created_at"));
            plan.setUpdatedAt(result.getTimestamp("updated_at"));
            List<PlanInspectorModel> inspectors = new ArrayList<>();
            User user = new User();
            if (CheckFieldExist.checkExist(result, "company_name")) {
                user.setCompanyId(result.getString("company_id"));
                user.setCompanyName(result.getString("company_name"));
            }
            plan.setUser(user);
            while (!result.isAfterLast() &&
                    CheckFieldExist.checkExist(result, "inspector") &&
                    result.getString("plan_id").equals(result.getString("plan"))
            ) {
                PlanInspectorModel temp = new PlanInspectorModel();
                temp.setUserId(result.getString("inspector"));
                temp.setAction(result.getInt("status"));
                inspectors.add(temp);
                result.next();
            }
            plan.setInspectors(inspectors);
            return plan;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
