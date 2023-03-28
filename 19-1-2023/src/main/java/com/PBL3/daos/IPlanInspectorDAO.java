package com.PBL3.daos;

import com.PBL3.models.PlanInspectorModel;

public interface IPlanInspectorDAO {
    void createOne(PlanInspectorModel domain);

    PlanInspectorModel findOne(String userId, String planId);

    void updateOne(PlanInspectorModel domain);

    void inactiveInspector(String userId, String planId);
}
