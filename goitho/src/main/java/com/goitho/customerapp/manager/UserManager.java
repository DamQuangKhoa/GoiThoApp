package com.goitho.customerapp.manager;

import android.text.TextUtils;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.model.EmployeeEntity;
import com.demo.architect.data.model.FarmerEntity;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.constants.Constants;
import com.google.gson.Gson;

/**
 * Created by Skull on 06/01/2018.
 */

public class UserManager {
    private static UserManager instance;
    private EmployeeEntity employeeUser;
    private String selectedFarmerId;
    private FarmerEntity farmerUser;

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public UserManager() {
        Gson gson = new Gson();
        String farmerEntityJsonString = SharedPreferenceHelper.getInstance(CoreApplication
                .getInstance()).getString(Constants.KEY_FARMER_ENTITY, "");
        if (!TextUtils.isEmpty(farmerEntityJsonString)) {
            this.farmerUser = gson.fromJson(farmerEntityJsonString, FarmerEntity.class);
        }
        String employeeEntityJsonString = SharedPreferenceHelper.getInstance(CoreApplication
                .getInstance()).getString(Constants.KEY_EMPLOYEE_ENTITY, "");
        if (!TextUtils.isEmpty(employeeEntityJsonString)) {
            this.employeeUser = gson.fromJson(employeeEntityJsonString, EmployeeEntity.class);
        }
        this.selectedFarmerId = SharedPreferenceHelper.getInstance(CoreApplication
                .getInstance()).getString(Constants.KEY_SELECTED_FARMER_ID, "");

    }

    public EmployeeEntity getEmployeeUser() {
        return employeeUser;
    }

    public void setEmployeeUser(EmployeeEntity employeeUser) {
        this.employeeUser = employeeUser;
        SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).pushString(Constants.KEY_EMPLOYEE_ENTITY
                , new Gson().toJson(employeeUser));
        SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).pushString(Constants.KEY_FARMER_ENTITY, "");
        this.farmerUser = null;
    }

    public FarmerEntity getFarmerUser() {
        return farmerUser;
    }

    public void setFarmerUser(FarmerEntity farmerUser) {
        this.farmerUser = farmerUser;
        this.employeeUser = null;

        SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).pushString(Constants.KEY_FARMER_ENTITY
                , new Gson().toJson(farmerUser));
        SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).pushString(Constants.KEY_EMPLOYEE_ENTITY, "");
    }

    public String getSelectedFarmerId() {
        return selectedFarmerId;
    }

    public void setSelectedFarmerId(String selectedFarmerId) {
        SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).pushString(Constants.KEY_SELECTED_FARMER_ID, selectedFarmerId);
        this.selectedFarmerId = selectedFarmerId;
    }
}
