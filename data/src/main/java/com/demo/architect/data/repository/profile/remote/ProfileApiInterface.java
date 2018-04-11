package com.demo.architect.data.repository.profile.remote;


import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.ResetPasswordEntity;
import com.demo.architect.data.model.UpdateUserEntity;
import com.demo.architect.data.model.UserEntity;
import com.demo.architect.data.model.VerificationEntity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Skull on 04/01/2018.
 */

public interface ProfileApiInterface {
    @Headers("Authorization: Basic Y2FsbGNlbnRlcl9nb2l0aG86Y2FsbCEjQCNAJCNAQEA=")
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/Tho/ResetPassword")
    Call<BaseResponse<ResetPasswordEntity>> resetPassword(@Field("userName") String username,
                                                          @Field("mobilePhone") String mobilePhone);

    @Headers("Authorization: Basic Y2FsbGNlbnRlcl9nb2l0aG86Y2FsbCEjQCNAJCNAQEA=")
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/Tho/ResetPhonenumber")
    Call<BaseResponse<VerificationEntity>> resetPhoneNumber(@Field("userName") String userName,
                                                            @Field("passWord") String passWord,
                                                            @Field("newPhonenumber") String newPhonenumber);

    @Headers("Authorization: Basic Y2FsbGNlbnRlcl9nb2l0aG86Y2FsbCEjQCNAJCNAQEA=")
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/Tho/ActiveResetPassword")
    Call<BaseResponse> activeResetPassword(@Field("userId") int userId,
                                           @Field("auth_code") String auth_code,
                                           @Field("new_password") String new_password);

    @Headers("Authorization: Basic Y2FsbGNlbnRlcl9nb2l0aG86Y2FsbCEjQCNAJCNAQEA=")
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/Tho/ActiveResetPhonenumber")
    Call<BaseResponse> activeResetPhoneNumber(@Field("userId") int userId,
                                              @Field("auth_code") String auth_code,
                                              @Field("newPhonenumber") String newPhonenumber);


    @Headers("Authorization: Basic Y2FsbGNlbnRlcl9nb2l0aG86Y2FsbCEjQCNAJCNAQEA=")
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/Tho/UpdateInfoUser")
    Call<BaseResponse<UpdateUserEntity>> updateUserInfo(@Field("userId") String userId,
                                                        @Field("userFullName") String userFullName,
                                                        @Field("avatarImage") String avatarImage,
                                                        @Field("address") String address,
                                                        @Field("userIdentify") String userIdentify,
                                                        @Field("email") String email,
                                                        @Field("imageIDFront") String imageIDFront,
                                                        @Field("imageIDBehind") String imageIDBehind,
                                                        @Field("listFields") ArrayList<UserEntity.Field> listFields,
                                                        @Field("userEduLevel") String userEduLevel);
}
