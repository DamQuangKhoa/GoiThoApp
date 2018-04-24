package com.demo.architect.data.repository.order.remote;


import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.ListBookingEntity;
import com.demo.architect.data.model.SaleEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Skull on 04/01/2018.
 */

public interface OrderApiInterface {

    @Headers("Authorization: Basic Y2FsbGNlbnRlcl9nb2l0aG86Y2FsbCEjQCNAJCNAQEA=")
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/User/setCalendar")
    Call<BaseResponse<ListBookingEntity>> setCalendar(@Field("userId") String userId,
                                                      @Field("contentFix") String contentFix,
                                                      @Field("dateFix") String dateFix,
                                                      @Field("saleId") String saleId,
                                                      @Field("addressFix") String addressFix,
                                                      @Field("phoneFix") String phoneFix,
                                                      @Field("nameFix") String nameFix);

    @Headers("Authorization: Basic Y2FsbGNlbnRlcl9nb2l0aG86Y2FsbCEjQCNAJCNAQEA=")
    @FormUrlEncoded
    @POST("https://callcenter2.goitho.com/api/User/checkSaleId")
    Call<BaseResponse<SaleEntity>> checkSaleId(@Field("saleId") String saleId);

}
