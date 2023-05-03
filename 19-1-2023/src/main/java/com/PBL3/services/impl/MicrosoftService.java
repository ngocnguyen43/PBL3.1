package com.PBL3.services.impl;

import com.PBL3.config.EnvConfig;
import com.PBL3.services.IMicrosoftService;
import com.PBL3.utils.exceptions.dbExceptions.UnexpectedException;
import com.PBL3.utils.helpers.JWTVerify;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.prefs.Preferences;

public class MicrosoftService implements IMicrosoftService {
    public static final String a = EnvConfig.load().get("ACCESS_TOKEN");

    public void savePreference(String value) {
        Preferences prefs = Preferences.userNodeForPackage(MicrosoftService.class);
        prefs.put("ACCESS_TOKEN", value);
    }

    public String readPreference() {
        Preferences prefs = Preferences.userNodeForPackage(MicrosoftService.class);
        return prefs.get("ACCESS_TOKEN", a);
    }

    public void updatePreference() {
        Preferences prefs = Preferences.userNodeForPackage(MicrosoftService.class);
        prefs.remove("ACCESS_TOKEN");
    }

    final String REFRESH_TOKEN_URI = EnvConfig.load().get("REFRESH_TOKEN_URI");
    final String REFRESH_TOKEN = EnvConfig.load().get("REFRESH_TOKEN");
    final String CLIENT_ID = EnvConfig.load().get("CLIENT_ID");
    final String CLIENT_SECRET = EnvConfig.load().get("CLIENT_SECRET");
    final String REDIRECT_URI = EnvConfig.load().get("REDIRECT_URI");

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
            JsonNode parent = new ObjectMapper().readTree(responseString);
            return parent.path("access_token").asText();
        });
    }

    @Override
    public String uploadFile(HttpServletRequest req) throws UnexpectedException, ServletException, IOException {
        String token = readPreference();
        long unixTime = Instant.now().getEpochSecond();
        long jwtExp = Objects.requireNonNull(Objects.requireNonNull(JWTVerify.verifyingJWT(token)).getClaim("exp").asLong());
        try {
            if (jwtExp < unixTime) {
                token = this.refreshToken();
                this.savePreference(token);
            }
        } catch (Exception e) {
            throw new UnexpectedException();
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Part filePart = req.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String[] fileNameSplits = fileName.split("\\.");
        int extensionIndex = fileNameSplits.length - 1;
        Path tempFilePath = Files.createTempFile(fileNameSplits[0], "." + fileNameSplits[extensionIndex]);
        File tempFile = tempFilePath.toFile();
        try (InputStream inputStream = filePart.getInputStream()) {
            Files.copy(inputStream, tempFilePath, StandardCopyOption.REPLACE_EXISTING);
        }
        String tempFilePathString = tempFile.getAbsolutePath();
        System.out.println(tempFilePathString);
        File file = new File(tempFilePathString);
        FileBody filebody = new FileBody(file, ContentType.create("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
        MultipartEntityBuilder entitybuilder = MultipartEntityBuilder.create();

        entitybuilder.addPart("file", filebody);
        HttpEntity entity = entitybuilder.build();
        ClassicHttpRequest httpPost = ClassicRequestBuilder
                .put("https://graph.microsoft.com/v1.0/drives/b!QpCqiREiSkWR4489RhSuOpHpdbqS0S1Plvao4GEb35GvSuhxD2rUQojXV0gDdSxJ/items/01Z3Q3AQYOUZAWCF2KYVC3RVKGNAX5CVQK:/" + fileNameSplits[0] + "." + fileNameSplits[1] + ":/content")
                .setEntity(entity)
                .setHeader("Authorization", "Bearer " + token)
                .build();
//        String content = null;
        String content = httpclient.execute(httpPost, response -> {
            System.out.println("----------------------------------------");
//                System.out.println("Login form get: " + response.getCode() + " " + response.getReasonPhrase());
//                EntityUtils.consume(response.getEntity());
            HttpEntity entityResponse = response.getEntity();
            String responseString = EntityUtils.toString(entityResponse, "UTF-8");
            System.out.println(responseString);
//                RefreshToken refreshToken = new Helper(responseString).toModel(RefreshToken.class);
//                return refreshToken.getRefreshToken();
            JsonNode parent = new ObjectMapper().readTree(responseString);
//            content = parent.asText();
//            System.out.println(parent.path("id").asText());
//            System.out.println(content);
            return parent.path("id").asText();
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
        return this.createShareLink(content, token);
    }

    @Override
    public String createShareLink(String itemId, String token) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("type", "view");
        node.put("scope", "anonymous");
        String JSON_STRING = node.toString();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(EnvConfig.load().get("CREATE_SHARE_LINK_URI") + itemId + "/createLink");
        HttpEntity stringEntity = new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON);
        httpPost.setHeader("Authorization", "Bearer " + token);
        httpPost.setEntity(stringEntity);
        httpclient.execute(httpPost, response -> {
            System.out.println("-----------------------------------------------");
            HttpEntity entityResponse = response.getEntity();
            String responseString = EntityUtils.toString(entityResponse, "UTF-8");
            System.out.println(responseString);
            JsonNode parent = new ObjectMapper().readTree(responseString);
            return null;
        });
        return null;
    }
}
