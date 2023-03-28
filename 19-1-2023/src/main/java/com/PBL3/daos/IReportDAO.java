package com.PBL3.daos;

import com.PBL3.models.ReportModel;

import java.util.List;

public interface IReportDAO extends GenericDAO<ReportModel> {
    void createOne(ReportModel domain);

    ReportModel findOneByPlanId(String id);

    List<ReportModel> findAll();
}
