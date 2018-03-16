package com.demo.architect.data.repository.bill.remote;

import com.demo.architect.data.model.IMDBEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by admin on 7/10/17.
 */

public interface BillApiInterface {
    @GET
    Call<IMDBEntity> getIMDB(@Url String url);
}
