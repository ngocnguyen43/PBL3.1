package com.PBL3.services.impl;

import com.PBL3.config.EnvConfig;
import com.PBL3.models.RefreshToken;
import com.PBL3.services.IGoogleApiService;
import com.PBL3.utils.helpers.Helper;
import com.PBL3.utils.helpers.IDGeneration;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.auth.Credentials;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.entity.mime.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;

public class GoogleApiService implements IGoogleApiService {
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final String CREDENTIALS_FILE_PATH = "C:\\Users\\minhn\\Desktop\\optimal-cabinet-352614-fa91e7e96f86.json";
    private static final List<String> SCOPES =
            Collections.singletonList(DriveScopes.DRIVE_METADATA_READONLY);
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        // Load client secrets.
        InputStream in = GoogleApiService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        //returns an authorized Credential object.
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
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
                System.out.println(responseString);
                RefreshToken refreshToken = new Helper(responseString).toModel(RefreshToken.class);
                return refreshToken.getAccess_token();

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
    public String refreshAccessToken() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://www.googleapis.com/upload/drive/v3/files?uploadType=multipart");

        FileBody fileBody1 = new FileBody(new File("/C:/Users/minhn/Desktop/a.json"), ContentType.APPLICATION_OCTET_STREAM);
        FileBody fileBody2 = new FileBody(new File("/C:/Users/minhn/Desktop/Plan Report.docx"), ContentType.APPLICATION_OCTET_STREAM);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addPart("file", fileBody1);
        builder.addPart("file", fileBody2);

        HttpEntity entity = builder.build();
        post.setEntity(entity);
        post.addHeader("Authorization", "Bearer ya29.a0AWY7Ckm2BPv2_xPF3erUN50TLFwkz_7Gfj-Yplec6vN1t5NQw7kyx_wgxYLcxUh0uZb_bZdasP478M-QHAnihRNre1xlIHLuJ-IZ-2IQN21F2360bJrtg-wWMAo_561l_j3k8NaKmsSjRsCtnVTJu5BGMBC_AIx-aCgYKAX4SARMSFQG1tDrpt-Xg4tl_aAuv5uXT_aOuVQ0167");
        post.addHeader("Content-Type", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");

        client.execute(post, response -> {
            System.out.println("----------------------------------------");
//                System.out.println("Login form get: " + response.getCode() + " " + response.getReasonPhrase());
//                EntityUtils.consume(response.getEntity());
            HttpEntity entityResponse = response.getEntity();
            String responseString = EntityUtils.toString(entityResponse, "UTF-8");
//                RefreshToken refreshToken = new Helper(responseString).toModel(RefreshToken.class);
//                return refreshToken.getRefreshToken();
            System.out.println(responseString);
            return null;
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
        return null;
    }

    @Override
    public String uploadFile(HttpServletRequest request) throws ServletException, IOException {
        final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.LEGACY);
        Part filePart = request.getPart("file");
//        if (filePart != null) {
//            InputStream fileContent = filePart.getInputStream();
//            String fileName = filePart.getSubmittedFileName();
//            FormBodyPart dataPart = FormBodyPartBuilder.create("", new InputStreamBody(fileContent, fileName)).build();
//            FormBodyPart configPart = FormBodyPartBuilder.create("", (ContentBody) new File(EnvConfig.load().get("GOOGLE_DRIVE_CONFIG_PATH"))).build();
//            builder.addPart(dataPart);
//            builder.addPart(configPart);
//        }
        InputStream fileContent = filePart.getInputStream();
        String fileName = filePart.getSubmittedFileName();
        String id = IDGeneration.generate();
        String[] fileNameSplits = fileName.split("\\.");

        int extensionIndex = fileNameSplits.length - 1;

        Path tempFilePath = Files.createTempFile(fileNameSplits[0],"."+ fileNameSplits[extensionIndex]);
        File tempFile = tempFilePath.toFile();
        try (InputStream inputStream = filePart.getInputStream()) {
            Files.copy(inputStream, tempFilePath, StandardCopyOption.REPLACE_EXISTING);
        }
        String tempFilePathString = tempFile.getAbsolutePath();
        System.out.println(tempFilePathString);

//        builder.addPart("file", fileContent,ContentType.APPLICATION_OCTET_STREAM, filePart.getName()).build();
//        File jsonConfig =  new File("C:\\Users\\minhn\\Desktop\\haha.json");
//        //FileBody fileBody = new FileBody(jsonConfig,ContentType.DEFAULT_BINARY);
//        InputStream targetStream = Files.newInputStream(jsonConfig.toPath());
//
//        builder.addBinaryBody("config",targetStream,ContentType.APPLICATION_OCTET_STREAM,jsonConfig.getName()).build();
////
//        final HttpEntity entity = builder.build();
        HttpEntity entity = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.LEGACY)
                .addPart("file",new FileBody(new File("C:\\Users\\minhn\\Desktop\\haha.json"), ContentType.create("application/json","UTF-8")))
                .addPart("file",new FileBody( new File("C:\\Users\\minhn\\.SmartTomcat\\PBL3.1\\january-8\\temp\\20-45514653006098995509.docx"), ContentType.create("application/vnd.openxmlformats-officedocument.wordprocessingml.document")))
                .build();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URI uri;
        try{
            uri = new URI("https://www.googleapis.com/upload/drive/v3/files?uploadType=multipart");
            ClassicHttpRequest httpPost = ClassicRequestBuilder.post()
                    .setUri(uri)
//                    .setHeader("Content-Type","")
                    .setHeader("Authorization","Bearer ya29.a0AWY7Ckngb-QXDrA3gtwIwE4oaxAMrKds-DsHxbTQ40J6Bu0StExfEwT1-T4XNleEsmcKK7KhLCP7rg3U2q-7g1unV0Rd4U6yaTULK4xnPTsR0Mpyk7450_oRs3B80VFD8QjfnhlVlOVqaKLUxa2ec58gWmvC4WfqaCgYKAeUSARMSFQG1tDrpHMaHXQ-HN8Qq5YavOZyLsg0167")
                    .setHeader("Content-Type","application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                    .setEntity(entity)
                    .build();
            httpclient.execute(httpPost, response -> {
                System.out.println("----------------------------------------");
//                System.out.println("Login form get: " + response.getCode() + " " + response.getReasonPhrase());
//                EntityUtils.consume(response.getEntity());
                HttpEntity entityResponse = response.getEntity();
                String responseString = EntityUtils.toString(entityResponse, "UTF-8");
//                RefreshToken refreshToken = new Helper(responseString).toModel(RefreshToken.class);
//                return refreshToken.getRefreshToken();
                System.out.println(responseString);
                return null;
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
        fileContent.close();
        return null;
    }

    @Override
    public void createPermissions() throws IOException {
        java.io.File credentail = new File("C:\\Users\\minhn\\Desktop\\optimal-cabinet-352614-fa91e7e96f86.json");
        InputStream json = Files.newInputStream (Paths.get("C:\\Users\\minhn\\Desktop\\optimal-cabinet-352614-fa91e7e96f86.json"));
//        GoogleClientSecrets googleClientSecrets = GoogleClientSecrets.load(new GsonFactory(),new InputStreamReader(
//                SampleClass.class.getResourceAsStream("/client_secrets.json"), "UTF-8"
//        ));
//        assert json != null;
        System.out.println(json == null);
        Credentials credentials = GoogleCredentials.fromStream(json);
//                .createScoped(Arrays.asList(DriveScopes.DRIVE_FILE));
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
                credentials);
        json.close();
        // Build a new authorized API client service.
        Drive service = new Drive.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                requestInitializer)
                .setApplicationName("Drive samples")
                .build();
        // Upload file photo.jpg on drive.
        com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
        fileMetadata.setName("photo.jpg");
        // File's content.
        java.io.File filePath = new java.io.File("C:\\Users\\minhn\\Desktop\\quan-b_i-10s23swi002_blue_5__1.jpg");
        // Specify media type and file-path for file.
        FileContent mediaContent = new FileContent("image/jpeg", filePath);
        try {
            com.google.api.services.drive.model.File file = service.files().create(fileMetadata, mediaContent)
                    .setFields("id")
                    .execute();
            System.out.println("File ID: " + file.getId());
//            return file.getId();
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            System.err.println("Unable to upload file: " + e.getDetails());
            throw e;
        }
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
    public  ContentBody convertToContentBody(Part part) throws IOException {
        InputStream inputStream = part.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        byte[] data = outputStream.toByteArray();
        return new ByteArrayBody(data, part.getSubmittedFileName());
    }
}
