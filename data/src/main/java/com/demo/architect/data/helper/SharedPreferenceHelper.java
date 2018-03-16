package com.demo.architect.data.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.demo.architect.data.model.EmployeeEntity;
import com.demo.architect.data.model.FarmerEntity;
import com.google.gson.Gson;

/**
 * Created by uyminhduc on 4/5/17.
 */

public class SharedPreferenceHelper {
    private static final String PREFERENCE_MAIN = "com.demo.uyminhduc.MAIN";
    private static final String MY_PREFERENCE = "com.demo.uyminhduc.MAIN.MY_PREFERENCE";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String FARMER_USER = "FARMER_USER";
    private static final String EMPLOYEE_USER = "EMPLOYEE_USER";
    private static final String WAS_STARTED = "WAS_STARTED";
    private SharedPreferences sharedPreferences;

    private static SharedPreferenceHelper _instance;

    public static SharedPreferenceHelper getInstance(Context context) {
        if (_instance == null) {
            _instance = new SharedPreferenceHelper(context);
        }
        return _instance;
    }

    public SharedPreferenceHelper(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCE_MAIN, Context.MODE_PRIVATE);
    }

    public void pushString(String key, String val) {
        sharedPreferences.edit().putString(key, val).apply();
    }

    public String getString(String key, String def) {
        return sharedPreferences.getString(key, def);
    }

    public void pushBoolean(String key, boolean bool) {
        sharedPreferences.edit().putBoolean(key, bool).apply();
    }

    public boolean getBoolean(String key, boolean def) {
        return sharedPreferences.getBoolean(key, def);
    }

    public void pushWasStartedBoolean(boolean bool) {
        sharedPreferences.edit().putBoolean(WAS_STARTED, bool).apply();
    }

    public boolean wasStartedBoolean(boolean def) {
        return sharedPreferences.getBoolean(WAS_STARTED, def);
    }

    public boolean existKey(String key) {
        return sharedPreferences.contains(key);
    }

    public void pushEmployeeObject( EmployeeEntity object) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        prefsEditor.putString(EMPLOYEE_USER, json);
        prefsEditor.commit();
    }

    public EmployeeEntity getEmployeeObject ( String def) {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(EMPLOYEE_USER, def);
        EmployeeEntity obj = gson.fromJson(json, EmployeeEntity.class);
        return obj;
    }

    public void pushFarmerObject( FarmerEntity object) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        prefsEditor.putString(FARMER_USER, json);
        prefsEditor.commit();
    }

    public FarmerEntity getFarmerObject ( String def) {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(FARMER_USER, def);
        FarmerEntity obj = gson.fromJson(json, FarmerEntity.class);
        return obj;
    }
}
