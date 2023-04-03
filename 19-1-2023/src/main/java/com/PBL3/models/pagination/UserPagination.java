package com.PBL3.models.pagination;

public class UserPagination extends AbstractPaginationModel {
    String fullname;
    String email;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
