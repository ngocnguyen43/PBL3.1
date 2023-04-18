package com.PBL3.dtos;

public class PostDTO {
    private String userId;
    private Integer isIncognito = 1;
    private String phoneNumber;
    private String typeId;
    private String title;
    private String content;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
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
}
