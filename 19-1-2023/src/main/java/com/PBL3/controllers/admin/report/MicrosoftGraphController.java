package com.PBL3.controllers.admin.report;

import com.PBL3.config.EnvConfig;
import com.PBL3.services.IMicrosoftService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.atomic.AtomicReference;

import static com.PBL3.utils.Constants.EndPoint.V1;

@WebServlet(urlPatterns = {V1  + "/test"})
@MultipartConfig
public class MicrosoftGraphController extends HttpServlet {
    @Inject
    private IMicrosoftService microsoftService;
    final  String url = "https://graph.microsoft.com/v1.0/drives/b!QpCqiREiSkWR4489RhSuOpHpdbqS0S1Plvao4GEb35GvSuhxD2rUQojXV0gDdSxJ/items/01Z3Q3AQYOUZAWCF2KYVC3RVKGNAX5CVQK:/zzz.docx:/content";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(microsoftService.refreshToken());
//            iGoogleApiService.configGoogleDrive("abc.docx");
//        ObjectMapper objectMapper = new ObjectMapper();
//        File jsonFile = new File("C:\\Users\\minhn\\Desktop\\haha.json");
//
//        try {
//            JsonNode root = objectMapper.readTree(jsonFile);
////            JsonNode steps = root.get("steps");
////            JsonNode item = root.get("name");
//            ((ObjectNode) root).put("name","1234.docx");
////            for (final JsonNode item : steps) {
////                if (item.findPath("stepId").asText().equals("1")) {
////                    ((ObjectNode) item).put("object", "Firefox");
////                }
////            }
//            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//            objectMapper.writeValue(new File("C:\\Users\\minhn\\Desktop\\haha.json"), root);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        CloseableHttpClient httpclient = HttpClients.custom().build();
//        URI uri;
//        try {
//            uri = new URI("http://localhost:8080/api/v1/private/products");
//            BasicHttpRequest httpUriRequest = BasicRequestBuilder.get().setUri(uri).build();
//            HttpGet httpGet = new HttpGet(uri);
//            httpGet.addHeader("ACCESS_TOKEN","eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJyb2xlIjoiQURNIiwiaXNzIjoibWluaG5nb2MiLCJleHAiOjE2ODA0MDk1MDMsImlhdCI6MTY4MDQwOTIwMywidXNlcklkIjoiMTBSMmgteF8zS0NfOFJPNGRBWjNIMnEifQ.b21RKgAlaCEGIwP5pwdmD21n9GxQkLWt-pKXprZh1lDjaKMawypkGYWJg54w4HEx4M5Qlt_bXCk7kfEM3_5KTdqeiCLuwwQmborY8PWlzoAOLl96iwnODwULUwCkg2B_KRNiuhKVeoh8FtIQAM-W8tQJe4ywnYc6XdYRgFRWtUOtbjD6NnATr2N6zGkqBMUMfd-iT1Eng51cYS8jyhkT-RGVYpA7bjtjiUma-6MnIdm4MVAY_t3eJhgGlpAeBB4z3maUUzvMggqmU_Vr7rqAjgkcuORsIjsb_fEt8I_FlVhQd_bvkHVMveI9H6rsBlTi1vbpQfqkV3sLc5kOWij-AA");
//            @SuppressWarnings("deprecation")
//            CloseableHttpResponse response = httpclient.execute(httpGet);
//            HttpEntity entity = response.getEntity();
//            String responseString = EntityUtils.toString(entity, "UTF-8");
//            System.out.println(responseString);
//            System.out.print("1");
//        } catch (URISyntaxException | IOException | org.apache.hc.core5.http.ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //MediaType mediaType = MediaType.parse("text/plain");
        File file = new File("C:\\Users\\minhn\\Desktop\\Linh Tinh\\PBL2.docx");
        InputStream inputStream = Files.newInputStream(file.toPath());
//        byte[] bytes = IOUtils.toByteArray(inputStream);
        HttpEntity entity = MultipartEntityBuilder.create()
                .addBinaryBody("file", inputStream, ContentType.APPLICATION_OCTET_STREAM, file.getName())
                .build();
        HttpPost request = new HttpPost("https://graph.microsoft.com/v1.0/drives/b!QpCqiREiSkWR4489RhSuOpHpdbqS0S1Plvao4GEb35GvSuhxD2rUQojXV0gDdSxJ/items/01Z3Q3AQYOUZAWCF2KYVC3RVKGNAX5CVQK:/4j.docx:/content");

        request.setEntity(entity);
        request.setHeader("Authorization","Bearer " + EnvConfig.load().get("ACCESS_TOKEN")
//                " eyJ0eXAiOiJKV1QiLCJub25jZSI6InRYVnYxSXhRdG41V0hxSHpjNFRsSjVZWVozb3h3QlZ4R056cWtVS2plQXMiLCJhbGciOiJSUzI1NiIsIng1dCI6Ii1LSTNROW5OUjdiUm9meG1lWm9YcWJIWkdldyIsImtpZCI6Ii1LSTNROW5OUjdiUm9meG1lWm9YcWJIWkdldyJ9.eyJhdWQiOiIwMDAwMDAwMy0wMDAwLTAwMDAtYzA" +
//                "wMC0wMDAwMDAwMDAwMDAiLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC80ZmI5MDk0MS02NzAwLTQ4MzktODUxMS1mMWQ0ZDU5ZTE0ZDcvIiwiaWF0IjoxNjgzMTAyNDgwLCJuYmYiOjE2ODMxMDI0ODAsImV4cCI6MTY4MzEwNzgyNCwiY" +
//                "WNjdCI6MCwiYWNyIjoiMSIsImFpbyI6IkFUUUF5LzhUQUFBQUxLZDJ3RzBmbkJlTWFuRldHM2NMTWdEM05YZDhLOXpJTVZkblVJVzAybjB3UEZqcUpjZDZ5TWptdEk5L2tUVnMiLCJhbXIiOlsicHdkIl0sImFwcF9kaXNwbGF5bmFtZSI6IlBCTDMiLCJhcHBpZCI6IjMyMjllMTYyLTY4NzYtNDY5Yy1hZjNmLTk5OTUwYzA4NDU" +
//                "1OSIsImFwcGlkYWNyIjoiMSIsImlkdHlwIjoidXNlciIsImlwYWRkciI6IjExMy4xNzQuMTczLjIwOSIsIm5hbWUiOiJtaW5obmdvYy40MDMwMyIsIm9pZCI6IjgzYTAwZDNjLWU2NTYtNDU1Zi05Nzg5LTBkYzEzYzNlN2UwMSIsInBsYXRmIjoiMyIsInB1aWQiOiIxMDAzMjAwMjk0Mjk1NzFFIiwicmgiOiIwLkFWWUFRUW01VHdC" +
//                "bk9VaUZFZkhVMVo0VTF3TUFBQUFBQUFBQXdBQUFBQUFBQUFDZkFPNC4iLCJzY3AiOiJGaWxlcy5SZWFkV3JpdGUuQWxsIHByb2ZpbGUgb3BlbmlkIGVtYWlsIiwic2lnbmluX3N0YXRlIjpbImttc2kiXSwic3ViIjoiRGdvV0lLZTBPaFR0dGJ1blkzYk9uT1p1QTl5ZEZOQ1Ztd09rdVg0azY4byIsInRlbmFudF9yZWdpb25fc2NvcG" +
//                "UiOiJBUyIsInRpZCI6IjRmYjkwOTQxLTY3MDAtNDgzOS04NTExLWYxZDRkNTllMTRkNyIsInVuaXF1ZV9uYW1lIjoibWluaG5nb2MuNDAzMDNAb25lZHJpdmUuaWVzc2Nob29sLmVkdS52biIsInVwbiI6Im1pbmhuZ29jLjQwMzAzQG9uZWRyaXZlLmllc3NjaG9vbC5lZHUudm4iLCJ1dGkiOiI4eWc0RUo3MW5rQ3FQLU5kRXRjakFBI" +
//                "iwidmVyIjoiMS4wIiwid2lkcyI6WyJiNzlmYmY0ZC0zZWY5LTQ2ODktODE0My03NmIxOTRlODU1MDkiXSwieG1zX3N0Ijp7InN1YiI6IkxRMUtmWWxscUhGamF3Y3BBcVNGYjJoNVBSV09tR1FsWVNKemdEZG5NUlkifSwieG1zX3RjZHQiOjE2NjM2NDQ2MjN9.JBD8DiElnGtxIaVhfBD509r8Rg6x1p4XdgFeoC3CulabJZcQk7h13a7Axy" +
//                "k1kzYr7zPdmMgYpYRhcdz0DNj7alGSbLON4DqCndD4OriInPU-pic_-HYoPznGn2dkkFYAHDb77CeUUivQxK4zNuxIudosbpQpg3w9snvFtgzeL3y1pr6PrfyrN9oPYwMGqRqyxaZ9ZVn0tm_yQSreEoSwLZZWBSeeAJVqqYHo-5uSAinFs1FO1pmnpnG5zPPctUlHd5bTqIYnfX4A10427BqzWR1AKV9gHvpp5kMO6YDnKdGAyBXA08ZeuMttpNNxBsJa6EvvYvc1wuM10vD8fhX6gQ"
                );
//        HttpResponse response = httpClient.execute(request);
        httpClient.execute(request, res -> {
            System.out.println("----------------------------------------");
//                System.out.println("Login form get: " + response.getCode() + " " + response.getReasonPhrase());
//                EntityUtils.consume(response.getEntity());
            HttpEntity entityResponse = res.getEntity();
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
        });    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Part filePart = req.getPart("file");
//        InputStream fileContent = filePart.getInputStream();

        String fileName = filePart.getSubmittedFileName();
        String[] fileNameSplits = fileName.split("\\.");
        int extensionIndex = fileNameSplits.length - 1;
        Path tempFilePath = Files.createTempFile(fileNameSplits[0],"."+ fileNameSplits[extensionIndex]);
        File tempFile = tempFilePath.toFile();
        try (InputStream inputStream = filePart.getInputStream()) {
            Files.copy(inputStream, tempFilePath, StandardCopyOption.REPLACE_EXISTING);
        }
        String tempFilePathString = tempFile.getAbsolutePath();
        System.out.println(tempFilePathString);
        File file = new File(tempFilePathString);
        FileBody filebody = new FileBody(file, ContentType.create("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
        MultipartEntityBuilder entitybuilder = MultipartEntityBuilder.create();

        //entitybuilder.setMode(HttpMultipartMode.LEGACY);
        entitybuilder.addPart("file", filebody);
        HttpEntity entity = entitybuilder.build();
        ClassicHttpRequest httpPost = ClassicRequestBuilder
                .put("https://graph.microsoft.com/v1.0/drives/b!QpCqiREiSkWR4489RhSuOpHpdbqS0S1Plvao4GEb35GvSuhxD2rUQojXV0gDdSxJ/items/01Z3Q3AQYOUZAWCF2KYVC3RVKGNAX5CVQK:/" + fileNameSplits[0] + "." + fileNameSplits[1] + ":/content")
                .setEntity(entity)
                .setHeader("Authorization","Bearer " + EnvConfig.load().get("ACCESS_TOKEN"))
                .build();
//        String content = null;
      String content =   httpclient.execute(httpPost, response -> {
            System.out.println("----------------------------------------");
//                System.out.println("Login form get: " + response.getCode() + " " + response.getReasonPhrase());
//                EntityUtils.consume(response.getEntity());
            HttpEntity entityResponse = response.getEntity();
            String responseString = EntityUtils.toString(entityResponse, "UTF-8");
//                RefreshToken refreshToken = new Helper(responseString).toModel(RefreshToken.class);
//                return refreshToken.getRefreshToken();
            JsonNode parent= new ObjectMapper().readTree(responseString);
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
        System.out.println(content);

    }
}
