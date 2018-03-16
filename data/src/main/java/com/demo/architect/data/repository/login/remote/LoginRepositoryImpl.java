package com.demo.architect.data.repository.login.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.EmployeeEntity;
import com.demo.architect.data.model.FarmerEntity;

import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 04/01/2018.
 */

public class LoginRepositoryImpl implements LoginRepository{
    private final static String TAG = LoginRepositoryImpl.class.getName();

    private LoginApiInterface mRemoteApiInterface;

    public LoginRepositoryImpl(LoginApiInterface mRemoteApiInterface) {
        this.mRemoteApiInterface = mRemoteApiInterface;
    }

    @Override
    public Observable<BaseResponse<FarmerEntity>> loginFarmer(final String username, final String password) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<FarmerEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<FarmerEntity>> subscriber) {
                handleLoginFarmerResponse(mRemoteApiInterface.loginFarmer(username, password), subscriber);
            }
        });
    }

    private void handleLoginFarmerResponse(Call<BaseResponse<FarmerEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<FarmerEntity> response = call.execute().body();
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
    public Observable<BaseResponse<EmployeeEntity>> loginEmployee(final String username, final String password) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<EmployeeEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<EmployeeEntity>> subscriber) {
                handleLoginEmployeeResponse(mRemoteApiInterface.loginEmployee(username, password), subscriber);
            }
        });
    }

    private void handleLoginEmployeeResponse(Call<BaseResponse<EmployeeEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<EmployeeEntity> response = call.execute().body();
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
