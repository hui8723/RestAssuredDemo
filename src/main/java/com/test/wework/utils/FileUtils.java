package com.test.wework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.util.HashMap;

public class FileUtils {

    /**
     * 从yaml文件中加载数据
     * @param path
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T loadYamlFile(String path,Class<T> valueType){
        //fixed: read from yaml or json
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(valueType.getResourceAsStream(path), valueType);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 从文件与map中获取数据
     * @param path
     * @param map 填充数据
     * @return
     */
    public static String getDataFromJsonFile(String path, HashMap<String, Object> map) {
        DocumentContext documentContext = JsonPath.parse(FileUtils.class.getResourceAsStream(path));
        map.entrySet().forEach(entry -> {
            documentContext.set(entry.getKey(),entry.getValue());
        });
        return documentContext.jsonString();
    }
}
