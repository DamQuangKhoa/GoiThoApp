package com.demo.architect.data.repository.login.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.EmployeeEntity;
import com.demo.architect.data.model.FarmerEntity;

import rx.Observable;

/**
 * Created by Skull on 04/01/2018.
 */

public interface LoginRepository {
    Observable<BaseResponse<FarmerEntity>> loginFarmer(String username, String password);

    Observable<BaseResponse<EmployeeEntity>> loginEmployee(String username, String password);
}
