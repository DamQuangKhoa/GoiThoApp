package com.demo.architect.data.repository.use.fertilizer.activity.remote;

import com.demo.architect.data.model.ActivityUseFertilizerEntity;
import com.demo.architect.data.model.BaseResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Skull on 14/01/2018.
 */

public interface ActivityUseFertilizerApiInterface {
    @POST("https://dtk.innoway.info/api/v1/activity_use_fertilizer")
    Call<BaseResponse<ActivityUseFertilizerEntity>> createActivity(@Header("access_token") String token, @Body RequestBody params);

    @PUT("https://dtk.innoway.info/api/v1/activity_use_fertilizer/{id}")
    Call<BaseResponse<ActivityUseFertilizerEntity>> updateActivity(@Header("access_token") String token, @Path("id") String id, @Body RequestBody params);

    @DELETE("https://dtk.innoway.info/api/v1/activity_use_fertilizer/{id}")
    Call<BaseResponse<ActivityUseFertilizerEntity>> deleteActivity(@Header("access_token") String token, @Path("id") String id);
}
