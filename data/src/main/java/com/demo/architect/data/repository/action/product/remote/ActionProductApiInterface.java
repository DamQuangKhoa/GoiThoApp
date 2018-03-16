package com.demo.architect.data.repository.action.product.remote;

import com.demo.architect.data.model.ActionEntity;
import com.demo.architect.data.model.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Skull on 08/01/2018.
 */

public interface ActionProductApiInterface {
    @GET("https://dtk.innoway.info/api/v1/product_action?fields=[\"$all\"]")
    //@Headers("access_token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXlsb2FkIjp7fSwicm9sZSI6ImFkbWluIiwiZXhwIjoiMjAxNy0xMi0yNVQwNDowNjozMS41ODNaIn0.8e6xt_7xCvO2iBmyrYUZB7LFAOk-GtR6d98xu0jISyE")
    Call<BaseResponse<List<ActionEntity>>> findListActionProduct(@Header("access_token") String token);

    @FormUrlEncoded
    @POST("https://dtk.innoway.info/api/v1/product_action")
    Call<BaseResponse<ActionEntity>> createProductAction(@Header("access_token") String token, @Field("name") String name);

    @DELETE("https://dtk.innoway.info/api/v1/product_action/{id}")
    Call<BaseResponse<ActionEntity>> deleteActionProduct(@Header("access_token") String token, @Path("id") String id);
}
