package com.PBL3.daos.impl;

import com.PBL3.daos.IReportDAO;
import com.PBL3.models.ReportModel;
import com.PBL3.utils.mapper.CountMapper;
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
        String sql = "SELECT * FROM reports WHERE plan_id = ?";
        List<ReportModel> reports = query(sql, new ReportMapper(), id);
        return reports.isEmpty() ? null : reports.get(0);
    }

    @Override
    public ReportModel findOneByReportId(String id) {
        String sql = "SELECT * FROM reports WHERE report_id = ?";
        List<ReportModel> reports = query(sql, new ReportMapper(), id);
        return reports.isEmpty() ? null : reports.get(0);
    }

    @Override
    public List<ReportModel> findAll() {
        String sql = "SELECT * FROM reports ORDER BY created_at DESC";
        return query(sql, new ReportMapper());
    }

    @Override
    public void updateReportStatus(String id) {
        String sql = "UPDATE login.reports SET status = 'accepted' WHERE report_id = ?";
        update(sql, id);
    }

    @Override
    public Integer countAllReports() {
        String sql = "SELECT count(report_id) FROM login.reports";
        List<Integer> pages = query(sql, new CountMapper());
        return pages.isEmpty() ? 0 : pages.get(0);
    }
}
