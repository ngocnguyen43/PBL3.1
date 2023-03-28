package com.PBL3.daos.impl;

import com.PBL3.daos.IReportDAO;
import com.PBL3.models.ReportModel;
import com.PBL3.utils.mapper.ReportMapper;

import java.util.List;

public class ReportDAO extends AbstractDAO<ReportModel> implements IReportDAO {
    @Override
    public void createOne(ReportModel domain) {
        String sql = "INSERT INTO login.reports (report_id,plan_id,title,path,modified_by) VALUES(?,?,?,?,?)";
        insert(sql, domain.getId(), domain.getPlanId(), domain.getTitle(), domain.getPath(), domain.getModifiedBy());
    }

    @Override
    public ReportModel findOneByPlanId(String id) {
        String sql = "SELECT * FROM login.reports WHERE plan_id = ?";
        List<ReportModel> reports = query(sql,new ReportMapper(),id);
        return reports.isEmpty() ? null : reports.get(0);
    }
}
