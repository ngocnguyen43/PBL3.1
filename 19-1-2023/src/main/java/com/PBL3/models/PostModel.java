package com.PBL3.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostModel extends AbstractModel {
    private String title;
    private String content;
    private String userId;
    private Integer isIncognito = 1;
    private String typeId;
    private PostTypeModel postTypeModel;
    private String phoneNUmber;

    private Integer action = 1;

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public PostTypeModel getPostTypeModel() {
        return postTypeModel;
    }

    public void setPostTypeModel(PostTypeModel postTypeModel) {
        this.postTypeModel = postTypeModel;
    }

    public String getPhoneNUmber() {
        return phoneNUmber;
    }

    public void setPhoneNUmber(String phoneNUmber) {
        this.phoneNUmber = phoneNUmber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getIsIncognito() {
        return isIncognito;
    }

    public void setIsIncognito(Integer isIncognito) {
        this.isIncognito = isIncognito;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
