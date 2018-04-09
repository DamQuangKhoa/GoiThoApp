package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yen on 4/4/2018.
 */

public class SendActiveEntity {
    @SerializedName("data")
    @Expose
    private Data data;

    public class Data {
        @SerializedName("account_confirm_key")
        @Expose
        private String key;

        @SerializedName("account_confirm_date")
        @Expose
        private String date;

    }

    public Data getData() {
        return data;
    }

    public String getKey() {
        return data.key;
    }

    public String getDate() {
        return data.date;
    }
}
