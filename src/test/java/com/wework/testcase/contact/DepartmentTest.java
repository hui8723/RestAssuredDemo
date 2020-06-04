package com.wework.testcase.contact;

import com.test.wework.contact.DepartmentApi;
import com.wework.category.Fast;
import com.wework.category.Slow;
import com.wework.testcase.BaseTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.hamcrest.Matchers.equalTo;

@DisplayName("部门模块测试")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentTest extends BaseTest {

    public static DepartmentApi departmentApi;
    private String id = "4";

    @BeforeAll
    public static void initAll() {
        departmentApi = new DepartmentApi();
    }

    @BeforeEach
    public void before() {
    }

    @DisplayName("查询部门用例")
    @Fast
    @Test
    @Order(0)
    public void listDepartmentTest() {
        departmentApi.list("").then().log().all().statusCode(200);
    }

    @DisplayName("创建部门测试用例")
    @Fast
    @Test
    @Order(1)
    public void createDepartmentTest() {
        departmentApi.create("创建" + departmentApi.random,"1")
        .then().log().all()
        .statusCode(200)
        .body("errcode",equalTo(0));
    }


    @DisplayName("删除测试用例")
    @Fast
    @Test
    public void deleteDepartmentTest() {
        departmentApi.initDataFromId(id);
        departmentApi.delete(id)
                .then().log().all()
                .statusCode(200)
                .body("errcode",equalTo(0));
    }

    @DisplayName("更新部门用例")
    @Fast
    @Test
    public void updateDepartmentTest() {
        departmentApi.initDataFromId(id);
        departmentApi.update("更新" + departmentApi.random,id)
                .then().log().all()
                .body("errcode",equalTo(0))
                .body("errmsg",equalTo("updated"));
    }

    @DisplayName("参数化创建部门用例")
    @Slow
    @ParameterizedTest
    @CsvFileSource(resources = "/data/create_dep.csv")
    public void createDepFromCsvTest(String name,String parentid) {
        departmentApi.create(name + departmentApi.random,parentid)
                .then().log().all()
                .statusCode(200)
                .body("errcode",equalTo(0));
    }


}
