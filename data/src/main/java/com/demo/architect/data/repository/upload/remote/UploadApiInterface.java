package com.demo.architect.data.repository.upload.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.UploadEntity;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Skull on 13/01/2018.
 */

public interface UploadApiInterface {
    @Headers("Authorization: Client-ID d4de8224fa0042f")
    @POST("https://api.imgur.com/3/image")
    Call<UploadEntity> uploadImage(@Body RequestBody file);
}
