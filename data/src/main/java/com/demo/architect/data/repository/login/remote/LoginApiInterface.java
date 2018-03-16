package com.demo.architect.data.repository.login.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.EmployeeEntity;
import com.demo.architect.data.model.FarmerEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Skull on 04/01/2018.
 */

public interface LoginApiInterface {
    @FormUrlEncoded
    @POST("https://dtk.innoway.info/api/v1/auth/login_farmer")
    Call<BaseResponse<FarmerEntity>> loginFarmer(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("https://dtk.innoway.info/api/v1/auth/login_employee")
    Call<BaseResponse<EmployeeEntity>> loginEmployee(@Field("username") String username, @Field("password") String password);
}
