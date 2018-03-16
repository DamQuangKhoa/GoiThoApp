package com.demo.architect.data.repository.farmer.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.FarmerEntity;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Skull on 05/01/2018.
 */

public interface FarmerApiInterface {
    @GET("https://dtk.innoway.info/api/v1/farmer?fields=[\"$all\", {\"employee\": [\"$all\"]}]")
    //@Headers("access_token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXlsb2FkIjp7fSwicm9sZSI6ImFkbWluIiwiZXhwIjoiMjAxNy0xMi0yNVQwNDowNjozMS41ODNaIn0.8e6xt_7xCvO2iBmyrYUZB7LFAOk-GtR6d98xu0jISyE")
    Call<BaseResponse<List<FarmerEntity>>> findListFarmer(@Header("access_token") String token);

    @GET("https://dtk.innoway.info/api/v1/farmer?fields=[\"$all\", {\"employee\": [\"$all\"]}]")
    Call<BaseResponse<List<FarmerEntity>>> findListFarmerByEmployeeId(@Header("access_token") String token, @Query("filter") String filter);

    @PUT("https://dtk.innoway.info/api/v1/farmer/{id}")
    Call<BaseResponse<FarmerEntity>> updateFarmer(@Header("access_token") String token, @Path("id") String id, @Body RequestBody params);
}
