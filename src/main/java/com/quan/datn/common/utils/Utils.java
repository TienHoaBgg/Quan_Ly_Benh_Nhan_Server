package com.quan.datn.common.utils;

import org.joda.time.LocalDateTime;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;
import java.text.SimpleDateFormat;


public class Utils {

    public static String getCurrentTimeStamp(){
        return new SimpleDateFormat("yyyy-M-dd HH:mm:ss").format(LocalDateTime.now().toDate());
    }

}
