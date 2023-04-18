package com.PBL3.daos.impl;

import com.PBL3.daos.IPlanDAO;
import com.PBL3.models.PlanModel;
import com.PBL3.utils.mapper.PlanMapper;

import java.util.List;

public class PlanDAO extends AbstractDAO<PlanModel> implements IPlanDAO {
    @Override
    public void createPlan(PlanModel domain) {
        String sql = "INSERT INTO plans (plan_id,company_id,action,time,path,modified_by) VALUES (?,?,?,?,?,?)";
        insert(sql, domain.getId(), domain.getCompanyId(), domain.getAction(), domain.getTime(), domain.getPath(), domain.getModifiedBy());
    }

    @Override
    public PlanModel findOneByPlanId(String id) {
        String sql = "select login.plans.*,login.plans_inspectors.user_id as inspector,login.plans_inspectors.action as status from login.plans inner join plans_inspectors on login.plans.plan_id = login.plans_inspectors.plan_id WHERE login.plans.plan_id = ?";
        List<PlanModel> plans = query(sql, new PlanMapper(), id);
        return plans.isEmpty() ? null : plans.get(0);

    }

    @Override
    public void updateTime(PlanModel domain) {
        String sql = "UPDATE plans SET time = ? WHERE plan_id = ?";
        update(sql, domain.getTime(), domain.getId());
    }

    @Override
    public void inactivePlan(String id) {
        PlanModel plan = findOneWithoutJoin(id);
        String sql = "UPDATE plans SET action = ? WHERE plan_id = ?";
        update(sql, plan.getAction() == 1 ? 0 : 1, id);
    }

    @Override
    public PlanModel findOneWithoutJoin(String id) {
        String sql = "SELECT * FROM login.plans WHERE plan_id = ?";
        List<PlanModel> plans = query(sql, new PlanMapper(), id);
        return plans.isEmpty() ? null : plans.get(0);
    }

    @Override
    public List<PlanModel> findAll() {
        String sql = "SELECT login.plans.* ,login.users.company_name FROM login.plans INNER JOIN login.users ON login.plans.company_id = login.users.company_id\n";
//        String sql = "select login.plans.*,login.plans_inspectors.plan_id as plan,login.plans_inspectors.user_id as inspector,login.plans_inspectors.action as status " +
//                "from login.plans " +
//                "left join plans_inspectors " +
//                "on login.plans.plan_id = login.plans_inspectors.plan_id";
        return query(sql, new PlanMapper());
    }

}
