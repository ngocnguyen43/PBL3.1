package com.PBL3.utils.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Data {
    @JsonProperty("ACCESS_TOKEN")
    private String accessToken;
    @JsonProperty("REFRESH_TOKEN")

    private String refreshToken;
    private String role;
    @JsonProperty("id")
    private String userId;
    private Object result = null;

    private Data() {
    }

    public String getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Object getResult() {
        return result;
    }

    public static class Builder {
        private String accessToken;
        private String refreshToken;

        private String role;
        private Object result;

        private String userId;

        public Builder(String token) {
            this.accessToken = token;
        }

        public Builder withAccessToken(String token) {
            this.accessToken = token;
            return this;
        }

        public Builder withRefreshToken(String token) {
            this.refreshToken = token;
            return this;
        }

        public Builder withId(String id) {
            this.userId = id;
            return this;
        }

        public Builder withResults(Object obj) {
            this.result = obj;
            return this;
        }

        public Builder withRole(String role) {
            this.role = role;
            return this;
        }

        public Data build() {
            Data body = new Data();
            body.accessToken = this.accessToken;
            body.refreshToken = this.refreshToken;
            body.result = this.result;
            body.role = this.role;
            body.userId = this.userId;
            return body;
        }
    }
}
