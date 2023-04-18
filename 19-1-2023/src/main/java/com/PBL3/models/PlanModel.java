package com.PBL3.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PlanModel extends AbstractModel {
    String companyId;
    Timestamp time;

    String path;

    Integer action;
    //    @JsonInclude(JsonInclude.Include.NON_EMPTY)

    @JsonProperty("company")
    User user;
    private List<PlanInspectorModel> inspectors;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public List<PlanInspectorModel> getInspectors() {
        return inspectors;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public void setInspectors(List<PlanInspectorModel> inspectors) {
        this.inspectors = inspectors;
    }
}
