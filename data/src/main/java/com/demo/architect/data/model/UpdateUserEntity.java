package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class UpdateUserEntity implements Serializable {
    @SerializedName("data")
    @Expose
    private UserEntity.UserInfo userInfo;

    public UserEntity.UserInfo getUserInfo(){
        return userInfo;
    }
}