package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;

/**
 * Created by uyminhduc on 10/23/16.
 */

public class BaseResponse<T> {
    @Expose
    private int code;
    @Expose
    private String description;
    @Expose
    private T response;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public T getResponse() {
        return response;
    }
}
