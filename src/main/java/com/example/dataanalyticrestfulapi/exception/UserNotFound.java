package com.example.dataanalyticrestfulapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class UserNotFound {

    @ResponseBody
    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String,String> getException(UserNotFoundException userNotFoundException){
        Map<String,String> exceptions=new HashMap<>();
        exceptions.put("errorMassage",userNotFoundException.getMessage());
        return exceptions;
    }
}
