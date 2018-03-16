package com.demo.architect.data.repository.product.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.ProductEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Skull on 07/01/2018.
 */

public interface ProductApiInterface {
    @GET("https://dtk.innoway.info/api/v1/product?fields=[\"$all\", {\"farmer\": [\"$all\"]}]")
    //@Headers("access_token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXlsb2FkIjp7fSwicm9sZSI6ImFkbWluIiwiZXhwIjoiMjAxNy0xMi0yNVQwNDowNjozMS41ODNaIn0.8e6xt_7xCvO2iBmyrYUZB7LFAOk-GtR6d98xu0jISyE")
    Call<BaseResponse<List<ProductEntity>>> findProduct(@Header("access_token") String token);
}
