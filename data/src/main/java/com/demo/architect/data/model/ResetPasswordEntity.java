package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResetPasswordEntity implements Serializable {
    @SerializedName("data")
    @Expose
    private VerificationEntity data;

    public VerificationEntity getData(){
        return data;
    }
}