package com.PBL3.model;

import java.sql.*;
import java.util.*;

public class Certificate extends AbstractModel {
	private String certificateId;
	private String name;
	private String description;
	private Blob image;
	private Boolean action;

	public String getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Boolean getAction() {
		return action;
	}

	public void setAction(Boolean action) {
		this.action = action;
	}

}
