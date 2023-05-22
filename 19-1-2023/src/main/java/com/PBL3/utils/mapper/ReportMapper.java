package com.PBL3.utils.mapper;

import com.PBL3.models.ReportModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportMapper implements IMapper<ReportModel> {
    @Override
    public ReportModel mapRow(ResultSet result) {
        ReportModel reportModel = new ReportModel();
        try {
            reportModel.setId(result.getString("report_id"));
            reportModel.setPlanId(result.getString("plan_id"));
            reportModel.setTitle(result.getString("title"));
            reportModel.setPath(result.getString("path"));
            reportModel.setStatus(result.getString("status"));
            reportModel.setModifiedBy(result.getString("modified_by"));
            reportModel.setCreatedAt(result.getTimestamp("created_at"));
            reportModel.setUpdatedAt(result.getTimestamp("updated_at"));
            return reportModel;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
