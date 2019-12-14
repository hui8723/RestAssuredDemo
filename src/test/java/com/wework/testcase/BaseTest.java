package com.wework.testcase;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/**
 * 实现登陆、注销；
 * 实现rest-assured的全局配置
 *
 */
public class BaseTest {

    public static String host;
    public static String port;


//    static {
//        // 用于加载properties文件
//        // 注意这里不需要文件扩展名.properties
//        ResourceBundle rb = ResourceBundle.getBundle("config");
//        serverHost = rb.getString("Host");
//        port = rb.getString("Port");
//    }

    @BeforeAll
    public static void initAll() {
//        String className = this.getClass().getName();
//        logger = Logger.getLogger(className);
//        PropertyConfigurator.configure("log4j.properties");
//        logger.setLevel(Level.DEBUG);
//        setBaseURI(); //设置Base URI
//        //设置Base Path，我这里是api（https://reqres.in/接口地址都是api开头，所以
//        //这里basepath设置api这个字符串），看看具体你自己项目请求地址结构
//        setBasePath("api");
//        setContentType(ContentType.JSON); //设置Content Type

    }



    @AfterAll
    public static void tearDownAll() {
//        resetBaseURI(tearDown);
//        resetBasePath();
    }

    public static void setBaseURI (){
        if("80".equals(port)) {
            RestAssured.baseURI = host;
        }else {
            RestAssured.baseURI = host+":"+port;
        }
    }

    public static void setBasePath(String basePath){
        RestAssured.basePath = basePath;
    }

    //重置BaseURI
    public static void resetBaseURI (){
        RestAssured.baseURI = null;
    }

    //重置basePath
    public static void resetBasePath(){
        RestAssured.basePath = null;
    }

}
