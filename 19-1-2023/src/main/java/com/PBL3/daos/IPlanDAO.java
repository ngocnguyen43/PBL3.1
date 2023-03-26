package com.PBL3.daos;

import com.PBL3.models.PlanModel;

import java.util.List;

public interface IPlanDAO extends GenericDAO<PlanModel> {
    void createPlan(PlanModel domain);
    PlanModel findOneByPlanId(String id);

    void updateTime(PlanModel domain);
    void inactivePlan(String id);

    PlanModel findOneWithoutJoin(String id);

    List<PlanModel> findAll();
}
