package com.PBL3.model;

import java.util.UUID;

public class StoreCertificate extends AbstractModel {
	private String id;

	private String certificateId;
	private String storeId;
	private Boolean action;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Boolean getAction() {
		return action;
	}

	public void setAction(Boolean action) {
		this.action = action;
	}

}
