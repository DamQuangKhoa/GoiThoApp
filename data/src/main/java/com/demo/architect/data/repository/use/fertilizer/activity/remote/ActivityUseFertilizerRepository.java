package com.demo.architect.data.repository.use.fertilizer.activity.remote;

import com.demo.architect.data.model.ActivityUseFertilizerEntity;
import com.demo.architect.data.model.BaseResponse;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by Skull on 14/01/2018.
 */

public interface ActivityUseFertilizerRepository {
    Observable<BaseResponse<ActivityUseFertilizerEntity>> createActivity(String token, HashMap<String, String> fields);
    Observable<BaseResponse<ActivityUseFertilizerEntity>> updateActivity(String token, String id, HashMap<String, String> fields);
    Observable<BaseResponse<ActivityUseFertilizerEntity>> deleteActivity(String token, String id);

}
