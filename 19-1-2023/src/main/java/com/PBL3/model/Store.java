package com.PBL3.model;

import java.util.UUID;

public class Store extends AbstractModel {
	private String storeId;
	private String storeName;
	private String storeAddress;
	private String number;
	private String description;
	private String action;
	private String kindof;

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getKindof() {
		return kindof;
	}

	public void setKindof(String kindof) {
		this.kindof = kindof;
	}

}
