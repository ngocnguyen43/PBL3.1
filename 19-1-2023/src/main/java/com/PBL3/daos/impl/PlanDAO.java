package com.PBL3.daos.impl;

import com.PBL3.daos.IPlanDAO;
import com.PBL3.models.PlanModel;

public class PlanDAO extends AbstractDAO<PlanModel> implements IPlanDAO {
    @Override
    public void createPlan(PlanModel domain) {
        String sql = "INSERT INTO plans (plan_id,company_id,action,time,path,modified_by) VALUES (?,?,?,?,?,?)";
        insert(sql,domain.getId(),domain.getCompanyId(),domain.getAction(),domain.getTime(),domain.getPath(),domain.getModifiedBy());
    }
}
