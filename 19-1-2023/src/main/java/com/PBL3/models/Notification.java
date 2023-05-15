package com.PBL3.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Notification extends AbstractModel {
    @JsonProperty("creator")
    private String creator;
    private List<String> mods;
    private List<String> users;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

    public static class Builder {
        private String creator;
        private List<String> mods;
        private List<String> users;
        private String id;
        private String modifiedBy;
        private String message;


        public Builder(String id) {
            this.id = id;
        }

        public Builder withMods(List<String> mods) {
            this.mods = mods;
            return this;
        }

        public Builder withUsers(List<String> users) {
            this.users = users;
            return this;
        }

        public Builder withModifiedBy(String id) {
            this.modifiedBy = id;
            return this;
        }

        public Builder withCreator(String id) {
            this.creator = id;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Notification build() {
            Notification notification = new Notification();
            notification.setId(this.id);
            notification.setCreator(this.creator);
            notification.setMods(this.mods);
            notification.setUsers(this.users);
            notification.setModifiedBy(this.modifiedBy);
            notification.setMessage(this.message);
            return notification;
        }
    }
}
