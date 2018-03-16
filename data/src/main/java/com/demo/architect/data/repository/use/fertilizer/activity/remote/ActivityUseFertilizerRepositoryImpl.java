package com.demo.architect.data.repository.use.fertilizer.activity.remote;

import com.demo.architect.data.model.ActivityUseFertilizerEntity;
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

public class ActivityUseFertilizerRepositoryImpl implements ActivityUseFertilizerRepository{
    private final static String TAG = ActivityUseFertilizerRepositoryImpl.class.getName();

    private ActivityUseFertilizerApiInterface mRemoteApiInterface;

    public ActivityUseFertilizerRepositoryImpl(ActivityUseFertilizerApiInterface mRemoteApiInterface) {
        this.mRemoteApiInterface = mRemoteApiInterface;
    }

    @Override
    public Observable<BaseResponse<ActivityUseFertilizerEntity>> createActivity(final String token, final HashMap<String, String> fields) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<ActivityUseFertilizerEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<ActivityUseFertilizerEntity>> subscriber) {
                JSONObject json = new JSONObject(fields);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json.toString());
                handleCreateActivityResponse(mRemoteApiInterface.createActivity(token, body), subscriber);
            }
        });
    }

    private void handleCreateActivityResponse(Call<BaseResponse<ActivityUseFertilizerEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<ActivityUseFertilizerEntity> response = call.clone().execute().body();
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
    public Observable<BaseResponse<ActivityUseFertilizerEntity>> updateActivity(final String token, final String id, final HashMap<String, String> fields) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<ActivityUseFertilizerEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<ActivityUseFertilizerEntity>> subscriber) {
                JSONObject json = new JSONObject(fields);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json.toString());
                handleUpdateActivityResponse(mRemoteApiInterface.updateActivity(token, id, body), subscriber);
            }
        });
    }

    private void handleUpdateActivityResponse(Call<BaseResponse<ActivityUseFertilizerEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<ActivityUseFertilizerEntity> response = call.clone().execute().body();
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
    public Observable<BaseResponse<ActivityUseFertilizerEntity>> deleteActivity(final String token, final String id) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<ActivityUseFertilizerEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<ActivityUseFertilizerEntity>> subscriber) {
                handleDeleteActivityResponse(mRemoteApiInterface.deleteActivity(token, id), subscriber);
            }
        });
    }

    private void handleDeleteActivityResponse(Call<BaseResponse<ActivityUseFertilizerEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<ActivityUseFertilizerEntity> response = call.execute().body();
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
