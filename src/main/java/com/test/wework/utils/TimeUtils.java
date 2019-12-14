package com.test.wework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

//    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String DATE_FORMAT = "yyyyMMddHHmmss";

    static Long curTime = System.currentTimeMillis();

    public static String getCurrentDate(String format) {
        Date date = new Date(curTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}
