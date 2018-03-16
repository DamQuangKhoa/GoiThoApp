package com.demo.architect.data.repository.activity.remote;

import com.demo.architect.data.model.ActivityEntity;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.HistoryActivitiesEntity;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Skull on 07/01/2018.
 */

public interface ActivityApiInterface {
    @GET("https://dtk.innoway.info/api/v1/activity?fields=[\"$all\", {\"farmer\": [\"$all\",  {\"employee\": [\"$all\"]}]},   {\"use_fertilizer\": [\"$all\"]}, {\"buy_fertilizer\": [\"$all\"]}, {\"product_action\": [\"$all\"]}, {\"product\": [\"$all\"]}]")
    Call<BaseResponse<List<ActivityEntity>>> findActivity(@Header("access_token") String token, @Query("filter") String filter);

    @GET("https://dtk.innoway.info/api/v1/activity/get_activity/{parent_id}?fields=[\"$all\", {\"farmer\": [\"$all\"]}, {\"product_action\": [\"$all\"]}]")
    Call<BaseResponse<HistoryActivitiesEntity>> findHistoryActivityById(@Header("access_token") String token, @Path("parent_id") String id);

    @POST("https://dtk.innoway.info/api/v1/activity/")
    Call<BaseResponse<ActivityEntity>> createActivity(@Header("access_token") String token, @Body RequestBody params);

    @DELETE("https://dtk.innoway.info/api/v1/activity/{id}")
    Call<BaseResponse<ActivityEntity>> deleteActivity(@Header("access_token") String token, @Path("id") String id);

    @PUT("https://dtk.innoway.info/api/v1/activity/{id}")
    Call<BaseResponse<ActivityEntity>> updateActivity(@Header("access_token") String token, @Path("id") String id, @Body RequestBody params);
}
