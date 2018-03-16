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
    private Results<T> results;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getResultObject() {
        return results != null ? (results.object != null ? results.object : results.objects != null ? results.objects.rows : null) : null;
    }

    private class Results<T> {
        @Expose
        private T object;
        @Expose
        private Objects<T> objects;
    }

    private class Objects<T> {
        @Expose
        private int count;
        @Expose
        private T rows;
    }
}
