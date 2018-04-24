package com.demo.architect.data.repository.notification.remote;


import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.ListNotificationsEntity;
import com.demo.architect.data.model.ListPromotionsEntity;
import com.demo.architect.data.model.NotificationEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Skull on 04/01/2018.
 */

public interface NotificationApiInterface {

    @Headers("Authorization: Basic Y2FsbGNlbnRlcl9nb2l0aG86Y2FsbCEjQCNAJCNAQEA=")
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/User/getListPromotion")
    Call<BaseResponse<ListPromotionsEntity>> getListPromotions(@Field("userId") String userId,
                                                               @Field("loaded") int loaded,
                                                               @Field("perload") int perload);

    @Headers("Authorization: Basic Y2FsbGNlbnRlcl9nb2l0aG86Y2FsbCEjQCNAJCNAQEA=")
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/v2/GoiTho/getListNotification")
    Call<BaseResponse<ListNotificationsEntity>> getListNotifications(@Field("userId") String userId,
                                                                     @Field("loaded") int loaded,
                                                                     @Field("perload") int perload);

    @Headers("Authorization: Basic Y2FsbGNlbnRlcl9nb2l0aG86Y2FsbCEjQCNAJCNAQEA=")
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/v2/GoiTho/getNewNotification")
    Call<BaseResponse<List<NotificationEntity>>> getNewNotifications(@Field("userId") String userId);


}
