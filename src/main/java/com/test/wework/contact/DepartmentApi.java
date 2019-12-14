package com.test.wework.contact;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;

public class DepartmentApi extends Contact {

    public DepartmentApi() {
        super();
        RestAssured.basePath = "department/";
    }

    public Response create(String name, String parentid) {
        return create(name,parentid,null);
    }

    public Response create(String name, String parentid,String id) {
        HashMap map = new HashMap();
        map.put("_file", "/data/create_department.json");
        map.put("name", name);
        map.put("parentid", parentid);
        if (id != null) map.put("id",id);
        return getResponseFromYaml("/api/create_department.yaml",map);
    }

    public Response update(String name, String id) {
        HashMap map = new HashMap();
        map.put("_file","/data/update_department.json");
        map.put("id",id);
        map.put("name",name);
        return getResponseFromYaml("/api/update_department.yaml",map);
    }

    public Response delete(String id) {
        HashMap map = new HashMap();
        map.put("id",id);
        return getResponseFromYaml("/api/delete_department.yaml",map);
    }

    public void deleteAll() {
        List<Integer> ids = list("").then().extract().path("department.id");
        System.out.println(ids);
        ids.forEach(id -> delete(id.toString()));
    }

    /**
     * 查询是否有此部门
     * @param departmentId
     * @return
     */
    public boolean hasDepartment(String departmentId) {
        List<Integer> ids = list(departmentId).then().extract().path("department.id");
        System.out.println(ids);
        return ids != null && ids.size() > 0;
    }

    public Response list(String id) {
        HashMap map = new HashMap();
        map.put("id",id);
        return getResponseFromYaml("/api/list_department.yaml",map);
    }

    public void initDataFromId(String id) {
        if (!hasDepartment(id)) {
            create("初始化" + random,"1",id);
        }
    }
}
