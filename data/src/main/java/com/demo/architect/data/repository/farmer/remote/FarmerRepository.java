package com.demo.architect.data.repository.farmer.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.FarmerEntity;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Skull on 05/01/2018.
 */

public interface FarmerRepository {
    Observable<BaseResponse<List<FarmerEntity>>> findListFarmer(String token);
    Observable<BaseResponse<List<FarmerEntity>>> findListFarmerByEmployeeId(String token, String filter);
    Observable<BaseResponse<FarmerEntity>> updateFarmer(String token, String id, HashMap<String, String> fields);
}
