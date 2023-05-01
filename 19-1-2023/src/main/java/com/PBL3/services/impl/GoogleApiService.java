package com.PBL3.services.impl;

import com.PBL3.config.EnvConfig;
import com.PBL3.models.RefreshToken;
import com.PBL3.services.IGoogleApiService;
import com.PBL3.utils.helpers.Helper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GoogleApiService implements IGoogleApiService {
    @Override
    public String getAccessToken() {
        final BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom().build();
        URI uri;
        try{
            uri = new URI(EnvConfig.load().get("REFRESH_TOKEN_URI"));
            ClassicHttpRequest httpPost = ClassicRequestBuilder.post()
                    .setUri(uri)
                    .addParameter("client_id", EnvConfig.load().get("CLIENT_ID"))
                    .addParameter("client_secret", EnvConfig.load().get("CLIENT_SECRET"))
                    .addParameter("grant_type", EnvConfig.load().get("GRANT_TYPE"))
                    .addParameter("refresh_token", EnvConfig.load().get("REFRESH_TOKEN"))
                    .build();
            httpclient.execute(httpPost, response -> {
                System.out.println("----------------------------------------");
//                System.out.println("Login form get: " + response.getCode() + " " + response.getReasonPhrase());
//                EntityUtils.consume(response.getEntity());
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, "UTF-8");
                RefreshToken refreshToken = new Helper(responseString).toModel(RefreshToken.class);
                return refreshToken.getRefreshToken();

//                System.out.println("Post logon cookies:");
//                final List<Cookie> cookies = cookieStore.getCookies();
//                if (cookies.isEmpty()) {
//                    System.out.println("None");
//                } else {
//                    for (int i = 0; i < cookies.size(); i++) {
//                        System.out.println("- " + cookies.get(i));
//                    }
//                }
            });
        } catch (URISyntaxException | java.io.IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public String refreshAccessToken() {
//        final File file = new File(url.getPath());
//        final FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY);
//
//        final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        builder.setMode(HttpMultipartMode.LEGACY);

        return null;
    }

    @Override
    public String uploadFile() {
        return null;
    }

    @Override
    public void createPermissions() {

    }

    @Override
    public void configGoogleDrive(String name) {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(EnvConfig.load().get("GOOGLE_DRIVE_CONFIG_PATH"));
        try {
            JsonNode root = objectMapper.readTree(jsonFile);
            ((ObjectNode) root).put("name",name);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(EnvConfig.load().get("GOOGLE_DRIVE_CONFIG_PATH")), root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
