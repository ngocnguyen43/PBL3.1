package com.PBL3.utils.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Message {
    private Meta meta;
    private Data data;
    private Pagination pagination;

    private Message() {
    }

    public Meta getMeta() {
        return meta;
    }

    public Data getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public static class Builder {
        private final Meta meta;
        private Data data;
        private Pagination pagination;

        public Builder(Meta meta) {
            this.meta = meta;
        }

        public Builder withData(Data data) {
            this.data = data;
            return this;
        }

        public Builder withPagination(Pagination pagination) {
            this.pagination = pagination;
            return this;
        }

        public Message build() {
            Message message = new Message();
            message.data = this.data;
            message.meta = this.meta;
            message.pagination = this.pagination;
            return message;
        }
    }

}
