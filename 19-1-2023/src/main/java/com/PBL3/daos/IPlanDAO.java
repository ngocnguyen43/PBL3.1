package com.PBL3.daos;

import com.PBL3.models.PlanModel;

public interface IPlanDAO extends GenericDAO<PlanModel> {
    void createPlan(PlanModel domain);
}
