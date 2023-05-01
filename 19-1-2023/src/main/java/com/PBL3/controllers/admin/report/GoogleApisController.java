package com.PBL3.controllers.admin.report;

import com.PBL3.services.IGoogleApiService;
import com.PBL3.services.impl.GoogleApiService;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicHttpRequest;
import org.apache.hc.core5.http.support.BasicRequestBuilder;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static com.PBL3.utils.Constants.EndPoint.*;

@WebServlet(urlPatterns = {V1  + "/test"})
public class GoogleApisController extends HttpServlet {
    @Inject
    private IGoogleApiService iGoogleApiService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //iGoogleApiService.getAccessToken();
            iGoogleApiService.configGoogleDrive("abc.docx");
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
}
