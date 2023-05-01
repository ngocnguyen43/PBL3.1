package com.PBL3.services;

public interface IGoogleApiService {
    String getAccessToken();
    String refreshAccessToken();
    String uploadFile();
    void createPermissions();
    void configGoogleDrive(String name);
}
