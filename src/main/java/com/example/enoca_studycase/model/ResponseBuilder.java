package com.example.enoca_studycase.model;

import java.util.List;

public class ResponseBuilder {

    private ResponseBuilder() {
    }

    public static <T> Response<T> build(T item) {
        return new Response<>(item);
    }

    public static <T> Response<T> build(String code, String message) {
        return new Response<>(code,message);
    }

    public static <T> Response<T> build(T item, String code, String message) {
        return new Response<>(item, code,message);
    }
}

