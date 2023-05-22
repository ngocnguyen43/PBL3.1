package com.PBL3.utils.response;

public class Pagination {
    private int totalPages;
    private int currentPage;
    private int totalResults;

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public static class Builder {
        private int totalPages;
        private int currentPage;
        private int totalResults;

        public Builder withTotalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public Builder withCurrentPage(int currentPages) {
            this.currentPage = currentPages;
            return this;
        }

        public Builder withTotalResults(int totalResults) {
            this.totalResults = totalResults;
            return this;
        }

        public Pagination build() {
            Pagination pagination = new Pagination();
            pagination.totalPages = this.totalPages;
            pagination.totalResults = this.totalResults;
            pagination.currentPage = this.currentPage;
            return pagination;
        }
    }

}
