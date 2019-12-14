package com.test.wework;

import com.test.wework.entity.Restful;
import com.test.wework.global.EnvConfig;
import com.test.wework.utils.FileUtils;
import com.test.wework.utils.TimeUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseApi {

    public static final String BODY_KEY = "_body";
    public static final String FILE_KEY = "_file";

    public String random= TimeUtils.getCurrentDate(TimeUtils.DATE_FORMAT);


    public BaseApi() {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = "https://qyapi.weixin.qq.com/cgi-bin/";
    }

    public RequestSpecification getDefaultRequestSpecification() {
        return given().log().all()
                .contentType(ContentType.JSON.withCharset("utf-8"));
    }



    public Response getResponseFromYaml(String path, HashMap<String,Object> map) {
        Restful restful = FileUtils.loadYamlFile(path,Restful.class);
        updateApiFromMap(restful,map);
        RequestSpecification requestSpecification = getDefaultRequestSpecification();

        if (restful.query != null) {
            restful.query.entrySet().forEach(stringStringEntry -> {
                requestSpecification.queryParam(stringStringEntry.getKey(),stringStringEntry.getValue());
            });
        }
        if (restful.body != null) {
            requestSpecification.body(restful.body);
        }
        String[] urls = updateUrl(restful.url);
        return requestSpecification
                .header("Host",urls[0])
                .when().request(restful.method,urls[1])
                .then().extract().response();


    }

    /**
     * 多环境支持，切换hosts
     * @param url
     * @return
     */
    private String[] updateUrl(String url) {
        HashMap<String, String> hosts= EnvConfig.getInstance().env.get(EnvConfig.getInstance().current);

        String host="";
        String urlNew="";
        for(Map.Entry<String, String> entry : hosts.entrySet()){
            if(url.contains(entry.getKey())){
                host=entry.getKey();
                urlNew=url.replace(entry.getKey(), entry.getValue());
            }
        }

        return new String[]{host, urlNew};



    }

    public Restful updateApiFromMap(Restful restful,HashMap<String,Object> map) {
        if (map == null) return restful;
        if (restful.method.toLowerCase().contains("get")) {
            map.entrySet().forEach(entry -> {
                restful.query.replace(entry.getKey(),entry.getValue().toString());
                System.out.println(restful.query);
            });
        }
        if (restful.method.toLowerCase().contains("post")) {
            if (map.containsKey(BODY_KEY)) {
                restful.body = map.get(BODY_KEY).toString();
            }
            if (map.containsKey(FILE_KEY)) {
                String filePath = map.get(FILE_KEY).toString();
                map.remove(FILE_KEY);
                restful.body = FileUtils.getDataFromJsonFile(filePath,map);
            }
        }
        return restful;
    }
}
