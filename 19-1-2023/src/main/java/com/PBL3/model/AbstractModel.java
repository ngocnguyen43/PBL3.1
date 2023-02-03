package com.PBL3.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public abstract class AbstractModel {
	private String modifiedBy;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
