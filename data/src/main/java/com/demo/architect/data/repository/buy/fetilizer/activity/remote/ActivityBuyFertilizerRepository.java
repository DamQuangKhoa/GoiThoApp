package com.demo.architect.data.repository.buy.fetilizer.activity.remote;

import com.demo.architect.data.model.ActivityBuyFertilizerEntity;
import com.demo.architect.data.model.BaseResponse;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by Skull on 14/01/2018.
 */

public interface ActivityBuyFertilizerRepository {
    Observable<BaseResponse<ActivityBuyFertilizerEntity>> createActivity(String token, HashMap<String, String> fields);
    Observable<BaseResponse<ActivityBuyFertilizerEntity>> updateActivity(String token, String id, HashMap<String, String> fields);
    Observable<BaseResponse<ActivityBuyFertilizerEntity>> deleteActivity(String token, String id);

}
