package com.demo.architect.data.repository.employee.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.EmployeeEntity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 05/01/2018.
 */

public class EmployeeRepositoryImpl implements EmployeeRepository{
    private final static String TAG = EmployeeRepositoryImpl.class.getName();

    private EmployeeApiInterface mRemoteApiInterface;

    public EmployeeRepositoryImpl(EmployeeApiInterface mRemoteApiInterface) {
        this.mRemoteApiInterface = mRemoteApiInterface;
    }

    @Override
    public Observable<BaseResponse<List<EmployeeEntity>>> findListEmployee(final String token) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<List<EmployeeEntity>>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<List<EmployeeEntity>>> subscriber) {
                handleGetListEmployeeResponse(mRemoteApiInterface.findListEmployee(token), subscriber);
            }
        });
    }

    private void handleGetListEmployeeResponse(Call<BaseResponse<List<EmployeeEntity>>> call, Subscriber subscriber) {
        try {
            BaseResponse<List<EmployeeEntity>> response = call.execute().body();
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
    public Observable<BaseResponse<EmployeeEntity>> updateEmployee(final String token, final String id, final HashMap<String, String> fields) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<EmployeeEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<EmployeeEntity>> subscriber) {
                JSONObject json = new JSONObject(fields);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json.toString());
                handleUpdateEmployeeResponse(mRemoteApiInterface.updateEmloyee(token, id, body), subscriber);
            }
        });
    }

    private void handleUpdateEmployeeResponse(Call<BaseResponse<EmployeeEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<EmployeeEntity> response = call.clone().execute().body();
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
