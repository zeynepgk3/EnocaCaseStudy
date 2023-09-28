package com.example.enoca_studycase.controller;

import com.example.enoca_studycase.model.Response;
import com.example.enoca_studycase.model.ResponseBuilder;


public abstract class BaseController {

    protected static <T> Response<T> respond(String code,String message) {
        return ResponseBuilder.build(code,message);
    }
    protected <T> Response<T> respond(T item, String code,String message) {
        return ResponseBuilder.build(item, code,message);
    }

}
