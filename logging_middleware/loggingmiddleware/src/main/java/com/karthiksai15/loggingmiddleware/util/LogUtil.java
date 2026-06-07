package com.karthiksai15.loggingmiddleware.util;

import com.karthiksai15.loggingmiddleware.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogUtil {
    private static LoggingService loggingService;

    @Autowired
    public LogUtil(LoggingService loggingService){
        LogUtil.loggingService=loggingService;
    }

    public static void log(String stack,String level,String packageName,String message){
                                   loggingService.log(stack,level,packageName,message);
    }
}