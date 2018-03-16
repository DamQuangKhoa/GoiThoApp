package com.demo.architect.data.repository.farmer.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.FarmerEntity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

import static com.demo.architect.data.helper.FilterStringHelper.getFilterString;

/**
 * Created by Skull on 05/01/2018.
 */

public class FarmerRepositoryImpl implements FarmerRepository{
    private final static String TAG = FarmerRepositoryImpl.class.getName();

    private FarmerApiInterface mRemoteApiInterface;

    public FarmerRepositoryImpl(FarmerApiInterface mRemoteApiInterface) {
        this.mRemoteApiInterface = mRemoteApiInterface;
    }

    @Override
    public Observable<BaseResponse<List<FarmerEntity>>> findListFarmer(final String token) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<List<FarmerEntity>>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<List<FarmerEntity>>> subscriber) {
                handleGetListFarmerResponse(mRemoteApiInterface.findListFarmer(token), subscriber);
            }
        });
    }

    @Override
    public Observable<BaseResponse<List<FarmerEntity>>> findListFarmerByEmployeeId(final String token, final String filter) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<List<FarmerEntity>>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<List<FarmerEntity>>> subscriber) {
                String filterString = getFilterString("employee_id", filter);
                handleGetListFarmerResponse(mRemoteApiInterface.findListFarmerByEmployeeId(token, filterString), subscriber);
            }
        });
    }

    private void handleGetListFarmerResponse(Call<BaseResponse<List<FarmerEntity>>> call, Subscriber subscriber) {
        try {
            BaseResponse<List<FarmerEntity>> response = call.execute().body();
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
    public Observable<BaseResponse<FarmerEntity>> updateFarmer(final String token, final String id, final HashMap<String, String> fields) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<FarmerEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<FarmerEntity>> subscriber) {
                JSONObject json = new JSONObject(fields);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json.toString());
                handleUpdateFarmerResponse(mRemoteApiInterface.updateFarmer(token, id, body), subscriber);
            }
        });
    }

    private void handleUpdateFarmerResponse(Call<BaseResponse<FarmerEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<FarmerEntity> response = call.clone().execute().body();
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
