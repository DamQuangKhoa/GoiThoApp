package com.demo.architect.data.repository.employee.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.EmployeeEntity;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Skull on 05/01/2018.
 */

public interface EmployeeApiInterface {
    @GET("https://dtk.innoway.info/api/v1/employee?fields=[\"$all\", {\"employee_farmers\": [\"$all\"]}]")
    Call<BaseResponse<List<EmployeeEntity>>> findListEmployee(@Header("access_token") String token);

    @PUT("https://dtk.innoway.info/api/v1/employee/{id}")
    Call<BaseResponse<EmployeeEntity>> updateEmloyee(@Header("access_token") String token, @Path("id") String id, @Body RequestBody params);
}
