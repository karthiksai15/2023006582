package com.karthiksai15.loggingmiddleware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoggingService{

    @Autowired
    private RestTemplate restTemplate;

    private static final String LOG_URL=
            "http://4.224.186.213/evaluation-service/logs";

    private static final String TOKEN="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJNYXBDbGFpbXMiOnsiYXVkIjoiaHR0cDovLzIwLjI0NC41Ni4xNDQvZXZhbHVhdGlvbi1zZXJ2aWNlIiwiZW1haWwiOiJrbW9wdXJAZ2l0YW0uaW4iLCJleHAiOjE3ODA4MTMyMzEsImlhdCI6MTc4MDgxMjMzMSwiaXNzIjoiQWZmb3JkIE1lZGljYWwgVGVjaG5vbG9naWVzIFByaXZhdGUgTGltaXRlZCIsImp0aSI6IjRhMDVmY2E3LWI4NzktNGNhYi1hNDM1LTIwYzRiZTZlZDUxOSIsImxvY2FsZSI6ImVuLUlOIiwibmFtZSI6Im1vcHVyIGthcnRoaWsgc2FpIiwic3ViIjoiYWQ0ZGUxMzMtMmYwZi00Nzk5LWE4ZWMtYmRhNjRiZTdlMDY2In0sImVtYWlsIjoia21vcHVyQGdpdGFtLmluIiwibmFtZSI6Im1vcHVyIGthcnRoaWsgc2FpIiwicm9sbE5vIjoiMjAyMzAwNjU4MiIsImFjY2Vzc0NvZGUiOiJ3Z0t0Z1oiLCJjbGllbnRJRCI6ImFkNGRlMTMzLTJmMGYtNDc5OS1hOGVjLWJkYTY0YmU3ZTA2NiIsImNsaWVudFNlY3JldCI6InhaUVJ5elBtRnF4V2RHZngifQ.TU26m75daN7sJMAGqMCHfeICk47lyWTFgRESSFSdiGc";

    public void log(String stack,String level,String packageName,String message){

        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(TOKEN);

        Map<String,String> body=new HashMap<>();

        body.put("stack",stack);
        body.put("level",level);
        body.put("package",packageName);
        body.put("message",message);

        HttpEntity<Map<String,String>> request=new HttpEntity<>(body,headers);

               restTemplate.postForObject(LOG_URL,request,String.class);
    }
}
