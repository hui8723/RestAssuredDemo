package com.test.wework.contact;

import io.restassured.response.Response;

import java.util.HashMap;

public class TagApi extends Contact {

    public Response create() {
        HashMap map = new HashMap();
        map.put(FILE_KEY,"/data/create_tag.json");
        map.put("tagid",100);
        map.put("tagname","UI");
        return getResponseFromYaml("/api/create-tag.yaml",map);
    }
}
