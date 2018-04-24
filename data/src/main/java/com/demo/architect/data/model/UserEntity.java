package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserEntity implements Serializable {
    @SerializedName("userInfo")
    @Expose
    private UserInfo userInfo;

    public UserEntity() {
    }

    public UserEntity(UserInfo userInfo){
        this.userInfo = userInfo;
    }

    public String getUserId() {
        return this.userInfo == null ? "" : userInfo.userId;
    }

    public void setUserId(String userId) {
        this.userInfo.userId = userId;
    }

    public String getAvatarImageUrl() {
        return userInfo == null ? "" : userInfo.avatarImageUrl;
    }

    public void setAvatarImageUrl(String avatarImageUrl) {
        this.userInfo.avatarImageUrl = avatarImageUrl;
    }

    public String getUserFullName() {
        return userInfo == null ? "" : userInfo.userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userInfo.userFullName = userFullName;
    }

    public String getAddress1() {
        return userInfo == null ? "" : userInfo.address1;
    }

    public void setAddress1(String address1) {
        this.userInfo.address1 = address1;
    }

    public String getAddress2() {
        return userInfo == null ? "" : userInfo.address2;
    }

    public void setAddress2(String address2) {
        this.userInfo.address2 = address2;
    }

    public String getEmail() {
        return userInfo == null ? "" : userInfo.email;
    }

    public void setEmail(String email) {
        this.userInfo.email = email;
    }

    public String getUserName() {
        return userInfo == null ? "" : userInfo.userName;
    }

    public void setUserName(String userName) {
        this.userInfo.userName = userName;
    }

    public String getMobilePhone() {
        return userInfo == null ? "" : userInfo.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.userInfo.mobilePhone = mobilePhone;
    }

    public class UserInfo {
        @SerializedName("userId")
        @Expose
        private String userId;

        @SerializedName("avatarImageUrl")
        @Expose
        private String avatarImageUrl;

        @SerializedName("userFullName")
        @Expose
        private String userFullName;

        @SerializedName("address1")
        @Expose
        private String address1;

        @SerializedName("address2")
        @Expose
        private String address2;

        @SerializedName("email")
        @Expose
        private String email;

        @SerializedName("userName")
        @Expose
        private String userName;

        @SerializedName("mobilePhone")
        @Expose
        private String mobilePhone;

    }

}