package com.PBL3.daos.impl;

import com.PBL3.daos.IPlanInspectorDAO;
import com.PBL3.models.PlanInspectorModel;
import com.PBL3.utils.mapper.PlanInspectorsMapper;

import java.util.List;

public class PlanInspectorDAO extends AbstractDAO<PlanInspectorModel> implements IPlanInspectorDAO {
    @Override
    public void createOne(PlanInspectorModel domain) {
        String sql = "INSERT INTO plans_inspectors (id,plan_id,user_id,action,modified_by) VALUES (?,?,?,?,?)";
        insert(sql, domain.getId(), domain.getPlanId(), domain.getUserId(), domain.getAction(), domain.getModifiedBy());
    }

    @Override
    public PlanInspectorModel findOne(String userId, String planId) {
        String sql = "SELECT * FROM plans_inspectors WHERE plan_id = ? AND user_id = ?";
        List<PlanInspectorModel> planInspectors = query(sql, new PlanInspectorsMapper(), planId, userId);
        return planInspectors.isEmpty() ? null : planInspectors.get(0);
    }

    @Override
    public void updateOne(PlanInspectorModel domain) {
        String sql = "UPDATE plans_inspectors SET ";
    }

    @Override
    public void inactiveInspector(String userId, String planId) {
        String sql = "UPDATE plans_inspectors SET action = ? WHERE user_id = ? AND plan_id = ?";
        PlanInspectorModel old = findOne(userId, planId);
        update(sql, old.getAction() == 1 ? 0 : 1, userId, planId);
    }

}
