package com.PBL3.dtos;

import java.util.List;

public class NotificationDTO {
    private String creator;
    private List<String> mods;
    private List<String> users;
    private String modifiedBy;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<String> getMods() {
        return mods;
    }

    public void setMods(List<String> mods) {
        this.mods = mods;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
