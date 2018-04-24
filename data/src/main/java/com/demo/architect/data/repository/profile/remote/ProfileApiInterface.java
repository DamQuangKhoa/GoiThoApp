package com.demo.architect.data.repository.profile.remote;


import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.PointEntity;
import com.demo.architect.data.model.ResetPasswordEntity;
import com.demo.architect.data.model.UpdateUserEntity;
import com.demo.architect.data.model.VerificationEntity;

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
    @POST("https://callcenter2.goitho.com/api/User/ResetPhoneNumber")
    Call<BaseResponse<VerificationEntity>> resetPhoneNumber(@Field("userName") String userName,
                                                            @Field("passWord") String passWord,
                                                            @Field("newPhonenumber") String newPhonenumber);

    @Headers("Authorization: Basic Y2FsbGNlbnRlcl9nb2l0aG86Y2FsbCEjQCNAJCNAQEA=")
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/User/ActiveResetPhoneNumber")
    Call<BaseResponse> activeResetPassword(@Field("userId") String userId,
                                           @Field("auth_code") String auth_code,
                                           @Field("new_password") String new_password);

    @Headers("Authorization: Basic Y2FsbGNlbnRlcl9nb2l0aG86Y2FsbCEjQCNAJCNAQEA=")
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/Tho/ActiveResetPhonenumber")
    Call<BaseResponse> activeResetPhoneNumber(@Field("userId") String userId,
                                              @Field("auth_code") String auth_code,
                                              @Field("newPhonenumber") String newPhonenumber);


    @Headers("Authorization: Basic Y2FsbGNlbnRlcl9nb2l0aG86Y2FsbCEjQCNAJCNAQEA=")
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/Tho/UpdateInfoUser")
    Call<BaseResponse<UpdateUserEntity>> updateUserInfo(@Field("userId") String userId,
                                                        @Field("userFullName") String userFullName,
                                                        @Field("avatarImage") String avatarImage,
                                                        @Field("address1") String address,
                                                        @Field("address2") String address2,
                                                        @Field("userIdentify") String userIdentify,
                                                        @Field("email") String email);

    @Headers("Authorization: Basic Y2FsbGNlbnRlcl9nb2l0aG86Y2FsbCEjQCNAJCNAQEA=")
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/User/getInfoPoint")
    Call<BaseResponse<PointEntity>> getInfoPoint(@Field("userId") String userId);
}
