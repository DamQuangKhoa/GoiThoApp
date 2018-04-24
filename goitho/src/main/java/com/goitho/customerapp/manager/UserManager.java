package com.goitho.customerapp.manager;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.model.UserEntity;
import com.goitho.customerapp.app.CoreApplication;

public class UserManager {
    private UserEntity userEntity;
    private static UserManager instance;

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public void setUser(UserEntity user) {
        userEntity = user;
        SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).pushUserObject(userEntity);
    }

    public UserEntity getUser() {
        if (userEntity == null) {
            userEntity = SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).getUserObject();
        }
        return userEntity;
    }
}
