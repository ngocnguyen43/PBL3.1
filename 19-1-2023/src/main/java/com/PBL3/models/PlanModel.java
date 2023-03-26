package com.PBL3.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)

public class PlanModel extends AbstractModel{
    String companyId;
    Timestamp time;

    String path;

    Integer action;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }
    private List<PlanInspectorModel> inspectors;

    public List<PlanInspectorModel> getInspectors() {
        return inspectors;
    }

    public void setInspectors(List<PlanInspectorModel> inspectors) {
        this.inspectors = inspectors;
    }
}
