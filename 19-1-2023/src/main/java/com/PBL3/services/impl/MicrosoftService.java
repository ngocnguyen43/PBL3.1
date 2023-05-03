package com.PBL3.services.impl;

import com.PBL3.config.EnvConfig;
import com.PBL3.services.IMicrosoftService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class MicrosoftService implements IMicrosoftService {
    final  String REFRESH_TOKEN_URI = EnvConfig.load().get("REFRESH_TOKEN_URI");
    final  String REFRESH_TOKEN = EnvConfig.load().get("REFRESH_TOKEN");
    final  String CLIENT_ID = EnvConfig.load().get("CLIENT_ID");
    final  String CLIENT_SECRET = EnvConfig.load().get("CLIENT_SECRET");
    final  String REDIRECT_URI = EnvConfig.load().get("REDIRECT_URI");
    @Override
    public String refreshToken() throws IOException {

        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("grant_type", "refresh_token"));
        parameters.add(new BasicNameValuePair("refresh_token", REFRESH_TOKEN));
        parameters.add(new BasicNameValuePair("client_id", CLIENT_ID));
        parameters.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));
        parameters.add(new BasicNameValuePair("redirect_uri", REDIRECT_URI));

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpEntity entity = new UrlEncodedFormEntity(parameters);

        ClassicHttpRequest httpPost = ClassicRequestBuilder
                .post(REFRESH_TOKEN_URI)
                .setHeader("Content-Type", "application/x-www-form-urlencoded")
                .setEntity(entity)
                .build();
        return httpclient.execute(httpPost, response -> {
            System.out.println("----------------------------------------");
            HttpEntity entityResponse = response.getEntity();
            String responseString = EntityUtils.toString(entityResponse, "UTF-8");
            System.out.println(responseString);
            JsonNode parent= new ObjectMapper().readTree(responseString);
            return parent.path("access_token").asText();
        });
    }

    @Override
    public String uploadFile(HttpServletRequest request) {
        return null;
    }

    @Override
    public void createShareLink(String itemId) {

    }
}
