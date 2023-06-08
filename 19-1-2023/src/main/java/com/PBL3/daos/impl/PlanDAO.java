package com.PBL3.daos.impl;

import com.PBL3.daos.IPlanDAO;
import com.PBL3.models.PlanModel;
import com.PBL3.models.pagination.PlanPaginationModel;
import com.PBL3.utils.mapper.CountMapper;
import com.PBL3.utils.mapper.PlanMapper;

import java.util.List;

import static com.PBL3.utils.Constants.Pagination.PER_PAGE;

public class PlanDAO extends AbstractDAO<PlanModel> implements IPlanDAO {
    @Override
    public void createPlan(PlanModel domain) {
        String sql = "INSERT INTO plans (plan_id,company_id,action,time,path,modified_by) VALUES (?,?,?,?,?,?)";
        insert(sql, domain.getId(), domain.getCompanyId(), domain.getAction(), domain.getTime(), domain.getPath(), domain.getModifiedBy());
    }

    @Override
    public PlanModel findOneByPlanId(String id) {
        String sql =
                "select login.plans.*," +
                        "login.plans_inspectors.user_id as inspector," +
                        "login.plans_inspectors.action as status, " +
                        "login.plans_inspectors.plan_id as plan, " +
                        "login.users.company_name " +
                        "from login.plans " +
                        "LEFT join plans_inspectors " +
                        "on login.plans.plan_id = login.plans_inspectors.plan_id " +
                        "INNER JOIN login.users ON " +
                        "login.plans.company_id = login.users.company_id " +
                        "WHERE login.plans.plan_id = ?";
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
    public List<PlanModel> findAll(PlanPaginationModel domain) {
        String sql = "SELECT login.plans.* ," +
                "login.users.company_name " +
                "FROM login.plans " +
                "LEFT JOIN login.users ON " +
                "login.plans.company_id = login.users.company_id\n ORDER BY login.plans.created_at DESC " +
                "LIMIT " + PER_PAGE + " OFFSET " + (domain.getPage() - 1) * PER_PAGE;
        return query(sql, new PlanMapper());
    }

    @Override
    public List<PlanModel> findAll(PlanPaginationModel domain, String id) {
        String sql = "SELECT login.plans.*,login.users.company_name , login.plans_inspectors.user_id  FROM login.plans " +
                "LEFT JOIN login.users ON " +
                "login.plans.company_id = login.users.company_id " +
                "LEFT JOIN login.plans_inspectors\n" +
                "ON login.plans.plan_id = login.plans_inspectors.plan_id WHERE login.plans_inspectors.user_id = ? " +
                "ORDER BY created_at DESC " +
                "LIMIT " + PER_PAGE + " OFFSET " + (domain.getPage() - 1) * PER_PAGE;

        return query(sql, new PlanMapper(), id);
    }

    @Override
    public Integer countAll() {
        String sql = "SELECT COUNT(*) as total " +
                "FROM login.plans " +
                "LEFT JOIN login.users ON " +
                "login.plans.company_id = login.users.company_id\n";
        List<Integer> pages = query(sql, new CountMapper());
        return pages.isEmpty() ? null : pages.get(0);
    }

    @Override
    public Integer countAll(String id) {
        String sql = "SELECT COUNT(*) as total FROM login.plans LEFT JOIN login.plans_inspectors\n" +
                "ON login.plans.plan_id = login.plans_inspectors.plan_id WHERE user_id = ?";
        List<Integer> pages = query(sql, new CountMapper(), id);
        return pages.isEmpty() ? null : pages.get(0);
    }

}
