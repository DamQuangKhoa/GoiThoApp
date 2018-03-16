package com.demo.architect.data.repository.activity.remote;

import com.demo.architect.data.model.ActivityEntity;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.HistoryActivitiesEntity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

import static com.demo.architect.data.helper.FilterStringHelper.getFilterString;
import static com.demo.architect.data.helper.FilterStringHelper.mergeFilterStrings;

/**
 * Created by Skull on 07/01/2018.
 */

public class ActivityRepositoryImpl implements ActivityRepository {
    private final static String TAG = ActivityRepositoryImpl.class.getName();

    private ActivityApiInterface mRemoteApiInterface;

    public ActivityRepositoryImpl(ActivityApiInterface mRemoteApiInterface) {
        this.mRemoteApiInterface = mRemoteApiInterface;
    }

    @Override
    public Observable<BaseResponse<List<ActivityEntity>>> findActivity(final String token, final String farmerId) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<List<ActivityEntity>>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<List<ActivityEntity>>> subscriber) {
                String filterString = mergeFilterStrings
                        (getFilterString("farmer_id", farmerId), getFilterString("parent_id", null));
                handleFindActivityResponse(mRemoteApiInterface.findActivity(token, filterString), subscriber);
            }
        });
    }

    @Override
    public Observable<BaseResponse<HistoryActivitiesEntity>> findHistoryActivityById(final String token, final String id) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<HistoryActivitiesEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<HistoryActivitiesEntity>> subscriber) {
                handleFindHistoryActivityResponse(mRemoteApiInterface.findHistoryActivityById(token, id), subscriber);
            }
        });
    }

    @Override
    public Observable<BaseResponse<ActivityEntity>> createActivity(final String token, final HashMap<String, String> fields) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<ActivityEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<ActivityEntity>> subscriber) {
                JSONObject json = new JSONObject(fields);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json.toString());
                handleCreateActivityResponse(mRemoteApiInterface.createActivity(token, body), subscriber);
            }
        });
    }

    @Override
    public Observable<BaseResponse<ActivityEntity>> deleteActivity(final String token, final String id) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<ActivityEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<ActivityEntity>> subscriber) {
                handleDeleteActivityResponse(mRemoteApiInterface.deleteActivity(token, id), subscriber);
            }
        });
    }

    @Override
    public Observable<BaseResponse<ActivityEntity>> updateActivity(final String token, final String id, final HashMap<String, String> fields) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<ActivityEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<ActivityEntity>> subscriber) {
                JSONObject json = new JSONObject(fields);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json.toString());
                handleUpdateActivityResponse(mRemoteApiInterface.updateActivity(token, id, body), subscriber);
            }
        });
    }

    private void handleFindHistoryActivityResponse(Call<BaseResponse<HistoryActivitiesEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<HistoryActivitiesEntity> response = call.execute().body();
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

    private void handleFindActivityResponse(Call<BaseResponse<List<ActivityEntity>>> call, Subscriber subscriber) {
        try {
            BaseResponse<List<ActivityEntity>> response = call.execute().body();
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

    private void handleCreateActivityResponse(Call<BaseResponse<ActivityEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<ActivityEntity> response = call.clone().execute().body();
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

    private void handleDeleteActivityResponse(Call<BaseResponse<ActivityEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<ActivityEntity> response = call.clone().execute().body();
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

    private void handleUpdateActivityResponse(Call<BaseResponse<ActivityEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<ActivityEntity> response = call.clone().execute().body();
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
