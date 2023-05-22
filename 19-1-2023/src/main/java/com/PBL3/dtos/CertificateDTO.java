package com.PBL3.dtos;

public class CertificateDTO {
    private String id;
    private String name;
    private String description;
    private String path;
    private Boolean action = true;

    public String getId() {
        return id;
    }

    public void setId(String certificateId) {
        this.id = certificateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getAction() {
        return action;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }

}
