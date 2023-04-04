package com.PBL3.dtos.pagination;

public abstract class AbstractPagination {
    Integer page = 1;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
