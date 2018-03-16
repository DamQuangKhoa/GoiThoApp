package com.demo.architect.data.repository.certificate.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.CertificateEntity;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Skull on 14/01/2018.
 */

public interface CertificateApiInterface {
    @GET("https://dtk.innoway.info/api/v1/certificate?fields=[\"$all\",  {\"type\": [\"$all\"]}]")
    Call<BaseResponse<List<CertificateEntity>>> findListCertificateByFarmerId(@Header("access_token") String token, @Query("filter") String filter);

    @POST("https://dtk.innoway.info/api/v1/certificate")
    Call<BaseResponse<CertificateEntity>> createCertificate(@Header("access_token") String token, @Body RequestBody params);

    @PUT("https://dtk.innoway.info/api/v1/certificate/{id}")
    Call<BaseResponse<CertificateEntity>> updateCertificate(@Header("access_token") String token, @Path("id") String id, @Body RequestBody params);

}
