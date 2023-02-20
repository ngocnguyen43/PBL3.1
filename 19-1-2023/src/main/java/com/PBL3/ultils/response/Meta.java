package com.PBL3.ultils.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "message", "error_code", "status_code" })
public class Meta {

	private int status_code;

	private int error_code ;

	private String error;
	private String message;

	private Meta() {
	}

	public int getStatusCode() {
		return this.status_code;
	}

	public int getErrCode() {
		return this.error_code;
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
		private int status_code;
		private int error_code ;
		private String message;
		private String error;

		public Builder(int status_code) {
			this.status_code = status_code;
		}

		public Builder withErrCode(int errCode) {
			this.error_code = errCode;
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
			meta.status_code = this.status_code;
			meta.error_code = this.error_code;
			meta.message = this.message;
			meta.error = this.error;
			return meta;
		}
	}

}
