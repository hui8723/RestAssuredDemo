package com.wework.testcase.contact;

import com.test.wework.contact.UserApi;
import com.wework.category.Fast;
import com.wework.category.Slow;
import com.wework.testcase.BaseTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

@DisplayName("测试创建用户")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreateUserTest extends BaseTest {

    static UserApi userApi;

    @BeforeAll
    public static void initAll() {
        userApi = new UserApi();
    }

    @DisplayName("创建一个用户")
    @Fast
    @Order(0)
    @Test
    public void test_create(){
        create("",null);
    }

    @DisplayName("从数组创建")
    @Slow
    @Order(1)
    @ParameterizedTest
    @ValueSource(strings = {"testAAA","testBBB","testCCC"})
    public void test_create01(String name) {
        create(name,null);
    }

    @DisplayName("从csv中创建")
    @Slow
    @Order(2)
    @ParameterizedTest
    @CsvFileSource(resources = "/data/users.csv")
    public void test_create02(String name,String alias) {
        create(name,alias);
    }

    private void create(String name,String alias) {
        String nameNew=name+userApi.random;
        String random=String.valueOf(System.currentTimeMillis()).substring(5+0, 5+8);
        HashMap<String, Object> map=new HashMap<>();
        map.put("userid", nameNew);
        map.put("name", nameNew);

        if (null != alias) {
            map.put("alias", alias);
        }
        map.put("department", Arrays.asList(1, 2));
        map.put("mobile", "151"+ random);
        map.put("userid", nameNew);
        map.put("email", random + "@qq.com");
        map.put("external_position",null);
        map.put("external_profile",null);
        userApi.create(map).then().log().all()
                .statusCode(200).body("errcode", equalTo(0));
    }
}
