package com.demo.architect.data.repository.auth.remote;


import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.VerificationEntity;
import com.demo.architect.data.model.SendActiveEntity;
import com.demo.architect.data.model.UserEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Skull on 04/01/2018.
 */

public interface AuthApiInterface {
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/Tho/Login")
    Call<BaseResponse<UserEntity>> login(@Field("userName") String username, @Field("passWord") String password);

    @Headers("Authorization: Basic Y2FsbGNlbnRlcl9nb2l0aG86Y2FsbCEjQCNAJCNAQEA=")
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/Tho/ActiveUser")
    Call<BaseResponse> activeUser(@Field("userId") String userId,
                                  @Field("auth_code") String authCode);

    @Headers("Authorization: Basic Y2FsbGNlbnRlcl9nb2l0aG86Y2FsbCEjQCNAJCNAQEA=")
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/Tho/SendActive")
    Call<BaseResponse<SendActiveEntity>> sendActive(@Field("userId") String userId);

    @Headers("Authorization: Basic Y2FsbGNlbnRlcl9nb2l0aG86Y2FsbCEjQCNAJCNAQEA=")
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/Tho/RegisterUser")
    Call<BaseResponse<VerificationEntity>> register(@Field("userName") String username,
                                                    @Field("passWord") String password,
                                                    @Field("mobilePhone") String mobilePhone);
}
