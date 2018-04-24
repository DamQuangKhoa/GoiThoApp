package com.demo.architect.data.repository.upload.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.UploadEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Skull on 13/01/2018.
 */

public interface UploadApiInterface {
    @Headers("Authorization: Client-ID d4de8224fa0042f")
    @POST("https://callcenter2.goitho.com/api/User/uploadImageSetCalendar")
    Call<BaseResponse<UploadEntity>> uploadImageSetCalendar(@Field("userId") String userId,
                                                            @Field("orderId") String orderId,
                                                            @Field("image") String image);
}
