package com.PBL3.models;

public class ReportModel extends AbstractModel {
    String planId;
    String title;
    String path;
    String status;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String plan_id) {
        this.planId = plan_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
