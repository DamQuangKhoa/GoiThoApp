package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;

/**
 * Created by Skull on 13/01/2018.
 */

public class UploadEntity {
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getImageUrl() {
        return data != null ? data.link : "";
    }

    private class Data {
        @Expose
        private String link;
    }

}
