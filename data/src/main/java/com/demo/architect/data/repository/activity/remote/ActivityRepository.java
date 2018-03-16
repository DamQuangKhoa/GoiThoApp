package com.demo.architect.data.repository.activity.remote;

import com.demo.architect.data.model.ActivityEntity;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.HistoryActivitiesEntity;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by Skull on 07/01/2018.
 */

public interface ActivityRepository {
    Observable<BaseResponse<List<ActivityEntity>>> findActivity(String token, String farmerId);
    Observable<BaseResponse<HistoryActivitiesEntity>> findHistoryActivityById(String token, String farmerId);
    Observable<BaseResponse<ActivityEntity>> createActivity(String token, HashMap<String, String> fields);
    Observable<BaseResponse<ActivityEntity>> deleteActivity(String token, String id);
    Observable<BaseResponse<ActivityEntity>> updateActivity(String token, String id, HashMap<String, String> fields);
}
