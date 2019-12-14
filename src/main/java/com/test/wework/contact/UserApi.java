package com.test.wework.contact;

import com.test.wework.utils.FileUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;

public class UserApi extends Contact {


    public UserApi() {
        super();
        RestAssured.basePath = "user/";
    }

//    创建成员
    public Response create(HashMap<String,Object> map) {
        String body = FileUtils.getDataFromJsonFile("/data/create_user.json",map);
        return getDefaultRequestSpecification().body(body)
                .when().post("create")
                .then().extract().response();
    }

//    读取成员
    public Response read(String userId) {
        return getDefaultRequestSpecification().param("userid",userId)
                .when().get("get")
                .then().extract().response();
    }


}
