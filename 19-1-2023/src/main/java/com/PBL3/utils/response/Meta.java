package com.PBL3.utils.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
//@JsonPropertyOrder({ "message", "error_code", "status_code" })
public class Meta {

    private Integer statusCode;
    @JsonInclude(value = Include.NON_DEFAULT)
    private Integer errorCode;


    private String error;
    private String message;

    private Meta() {
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public Integer getErrCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    public static class Builder {
        private final Integer statusCode;
        private Integer errorCode;
        private String message;
        private String error;

        public Builder(int status_code) {
            this.statusCode = status_code;
        }

        public Builder withErrCode(int errCode) {
            this.errorCode = errCode;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withError(String error) {
            this.error = error;
            return this;
        }

        public Meta build() {
            Meta meta = new Meta();
            meta.statusCode = this.statusCode;
            meta.errorCode = this.errorCode;
            meta.message = this.message;
            meta.error = this.error;
            return meta;
        }
    }

}
