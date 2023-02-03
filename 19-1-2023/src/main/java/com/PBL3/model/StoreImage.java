package com.PBL3.model;

import java.sql.Blob;
import java.util.UUID;

public class StoreImage {
	private String imageId;
	private String storeId;
	private Blob image;

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

}
