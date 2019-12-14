package com.test.wework.contact;

import com.test.wework.BaseApi;
import com.test.wework.global.WeWork;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSpecification;

public class Contact extends BaseApi {

    public RequestSpecification getDefaultRequestSpecification() {
        RequestSpecification requestSpecification = super.getDefaultRequestSpecification();
        requestSpecification.queryParam("access_token", WeWork.getToken())
                .accept(ContentType.JSON);
        requestSpecification.filter(new Filter() {
            public Response filter(FilterableRequestSpecification req, FilterableResponseSpecification res, FilterContext ctx) {
                return ctx.next(req, res);
            }
        });
        return requestSpecification;
    }
}
