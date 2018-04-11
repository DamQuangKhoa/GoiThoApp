package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class UserEntity implements Serializable {
    @SerializedName("userInfo")
    @Expose
    private UserInfo userInfo;

    public UserEntity(UserInfo userInfo){
        this.userInfo = userInfo;
    }

    public String getUserId() {
        return userInfo == null ? "" : userInfo.userId;
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

    public String getAddress() {
        return userInfo == null ? "" : userInfo.address;
    }

    public void setAddress(String address) {
        this.userInfo.address = address;
    }

    public String getUserIdentify() {
        return userInfo == null ? "" : userInfo.userIdentify;
    }

    public void setUserIdentify(String userIdentify) {
        this.userInfo.userIdentify = userIdentify;
    }

    public String getEmail() {
        return userInfo == null ? "" : userInfo.email;
    }

    public void setEmail(String email) {
        this.userInfo.email = email;
    }

    public String getImageIDFrontUrl() {
        return userInfo == null ? "" : userInfo.imageIDFrontUrl;
    }

    public void setImageIDFrontUrl(String imageIDFrontUrl) {
        this.userInfo.imageIDFrontUrl = imageIDFrontUrl;
    }

    public String getImageIDBehind() {
        return userInfo == null ? "" : userInfo.imageIDBehind;
    }

    public void setImageIDBehind(String imageIDBehind) {
        this.userInfo.imageIDBehind = imageIDBehind;
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

    public String getUserEduLevel() {
        return userInfo == null ? "" : userInfo.userEduLevel;
    }

    public void setUserEduLevel(String userEduLevel) {
        this.userInfo.userEduLevel = userEduLevel;
    }

    public ArrayList<Field> getListFields() {
        return userInfo == null ? new ArrayList<Field>() : userInfo.listFields;
    }

    public void setListFields(ArrayList<Field> listFields) {
        this.userInfo.listFields = listFields;
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

        @SerializedName("address")
        @Expose
        private String address;

        @SerializedName("userIdentify")
        @Expose
        private String userIdentify;

        @SerializedName("email")
        @Expose
        private String email;

        @SerializedName("imageIDFrontUrl")
        @Expose
        private String imageIDFrontUrl;

        @SerializedName("imageIDBehind")
        @Expose
        private String imageIDBehind;

        @SerializedName("userName")
        @Expose
        private String userName;

        @SerializedName("mobilePhone")
        @Expose
        private String mobilePhone;

        @SerializedName("userEduLevel")
        @Expose
        private String userEduLevel;

        @SerializedName("listFields")
        @Expose
        private ArrayList<Field> listFields;

        public UserInfo (){

        }
    }

    public class Field {
        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("name")
        @Expose
        private String name;
    }
}