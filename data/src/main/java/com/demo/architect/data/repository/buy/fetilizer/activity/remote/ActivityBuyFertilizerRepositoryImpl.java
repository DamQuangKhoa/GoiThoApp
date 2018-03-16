package com.demo.architect.data.repository.buy.fetilizer.activity.remote;

import com.demo.architect.data.model.ActivityBuyFertilizerEntity;
import com.demo.architect.data.model.BaseResponse;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 14/01/2018.
 */

public class ActivityBuyFertilizerRepositoryImpl implements ActivityBuyFertilizerRepository{
    private final static String TAG = ActivityBuyFertilizerRepositoryImpl.class.getName();

    private ActivityBuyFertilizerApiInterface mRemoteApiInterface;

    public ActivityBuyFertilizerRepositoryImpl(ActivityBuyFertilizerApiInterface mRemoteApiInterface) {
        this.mRemoteApiInterface = mRemoteApiInterface;
    }

    @Override
    public Observable<BaseResponse<ActivityBuyFertilizerEntity>> createActivity(final String token, final HashMap<String, String> fields) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<ActivityBuyFertilizerEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<ActivityBuyFertilizerEntity>> subscriber) {
                JSONObject json = new JSONObject(fields);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json.toString());
                handleCreateActivityResponse(mRemoteApiInterface.createActivity(token, body), subscriber);
            }
        });
    }

    private void handleCreateActivityResponse(Call<BaseResponse<ActivityBuyFertilizerEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<ActivityBuyFertilizerEntity> response = call.clone().execute().body();
            if (!subscriber.isUnsubscribed()) {
                if (response != null) {
                    subscriber.onNext(response);
                } else {
                    subscriber.onError(new Exception("Network Error!"));
                }
                subscriber.onCompleted();
            }
        } catch (Exception e) {
            if (!subscriber.isUnsubscribed()) {
                subscriber.onError(e);
                subscriber.onCompleted();
            }
        }
    }

    @Override
    public Observable<BaseResponse<ActivityBuyFertilizerEntity>> updateActivity(final String token, final String id, final HashMap<String, String> fields) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<ActivityBuyFertilizerEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<ActivityBuyFertilizerEntity>> subscriber) {
                JSONObject json = new JSONObject(fields);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json.toString());
                handleUpdateActivityResponse(mRemoteApiInterface.updateActivity(token, id, body), subscriber);
            }
        });
    }

    private void handleUpdateActivityResponse(Call<BaseResponse<ActivityBuyFertilizerEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<ActivityBuyFertilizerEntity> response = call.clone().execute().body();
            if (!subscriber.isUnsubscribed()) {
                if (response != null) {
                    subscriber.onNext(response);
                } else {
                    subscriber.onError(new Exception("Network Error!"));
                }
                subscriber.onCompleted();
            }
        } catch (Exception e) {
            if (!subscriber.isUnsubscribed()) {
                subscriber.onError(e);
                subscriber.onCompleted();
            }
        }
    }

    @Override
    public Observable<BaseResponse<ActivityBuyFertilizerEntity>> deleteActivity(final String token, final String id) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<ActivityBuyFertilizerEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<ActivityBuyFertilizerEntity>> subscriber) {
                handleDeleteActivityResponse(mRemoteApiInterface.deleteActivity(token, id), subscriber);
            }
        });
    }

    private void handleDeleteActivityResponse(Call<BaseResponse<ActivityBuyFertilizerEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<ActivityBuyFertilizerEntity> response = call.clone().execute().body();
            if (!subscriber.isUnsubscribed()) {
                if (response != null) {
                    subscriber.onNext(response);
                } else {
                    subscriber.onError(new Exception("Network Error!"));
                }
                subscriber.onCompleted();
            }
        } catch (Exception e) {
            if (!subscriber.isUnsubscribed()) {
                subscriber.onError(e);
                subscriber.onCompleted();
            }
        }
    }
}
