package com.karthiksai15.loggingmiddleware.controller;

import com.karthiksai15.loggingmiddleware.util.LogUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController{

    @GetMapping("/test-log")
    public String testLog(){
             LogUtil.log("backend","info","controller","Test log created");
                  return "Log Sent Successfully";
    }
}