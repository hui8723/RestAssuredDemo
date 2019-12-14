package com.test.wework.global;

import com.test.wework.utils.FileUtils;

import java.util.HashMap;

public class EnvConfig {

    public final static String TEST = "test";
    public final static String DEV = "dev";
    public final static String UAT = "uat";
    public final static String ONLINE = "online";

    public String agentId="1000005";
    public String secret="1JPyY9GvPLZfpvxEDjok-Xt_9v7HIBYJhZUoO6EgNGY";
    public String corpid = "ww5ce9262914bded0e";
    public String contactSecret="ODYjUUaj9kXAElmR_xxEulw1wFTgp-Kt4qdPZlDCSWU";

    public String current=DEV;
    public HashMap<String, HashMap<String, String>> env;

    private static EnvConfig envConfig;

    private EnvConfig() {}

    public static EnvConfig getInstance(){
        if(envConfig ==null){
            envConfig = FileUtils.loadYamlFile("/conf/WeworkConfig.yaml",EnvConfig.class);
            System.out.println(envConfig);
            System.out.println(envConfig.agentId);
        }
        return envConfig;
    }
}
