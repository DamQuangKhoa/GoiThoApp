package com.demo.architect.data.repository.base.remote;

import com.demo.architect.data.model.EmployeeEntity;
import com.demo.architect.data.model.FarmerEntity;
import com.demo.architect.data.model.IMDBEntity;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by uyminhduc on 10/16/16.
 */

public interface RemoteApiInterface {

    @GET("http://www.imdb.com/xml/find?json=1&nr=1&nm=on&q=jeniffer+garner")
    Call<IMDBEntity> getIMDB();

    @GET("http://dtk.com/api/v0/dtk/auth/farmer/:id")
    @Headers("token: asdnsakjdha")
    Call<FarmerEntity> loginFarmer(String email, String password);

    @GET("http://dtk.com/api/v0/dtk/auth/employee/:id")
    @Headers("token: asdnsakjdha")
    Call<EmployeeEntity> loginEmployee(String email, String password);

//    @POST("http://dtk.com/api/v0/dtk/image")
//    @Headers("token: asdnsakjdha")
//    Call<>
}
