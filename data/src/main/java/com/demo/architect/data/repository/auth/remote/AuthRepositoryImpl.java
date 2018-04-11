package com.demo.architect.data.repository.auth.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.VerificationEntity;
import com.demo.architect.data.model.SendActiveEntity;
import com.demo.architect.data.model.UserEntity;

import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 04/01/2018.
 */

public class AuthRepositoryImpl implements AuthRepository {
    private final static String TAG = AuthRepositoryImpl.class.getName();

    private AuthApiInterface mRemoteApiInterface;

    public AuthRepositoryImpl(AuthApiInterface mRemoteApiInterface) {
        this.mRemoteApiInterface = mRemoteApiInterface;
    }

    private void handleLoginResponse(Call<BaseResponse<UserEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<UserEntity> response = call.execute().body();
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
    public Observable<BaseResponse<UserEntity>> login(final String username, final String password) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<UserEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<UserEntity>> subscriber) {
                handleLoginResponse(mRemoteApiInterface.login(username, password), subscriber);
            }
        });
    }

    private void handleActiveUserResponse(Call<BaseResponse> call, Subscriber subscriber) {
        try {
            BaseResponse response = call.execute().body();
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
    public Observable<BaseResponse> activeUser(final String userId, final String authCode) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse>() {
            @Override
            public void call(Subscriber<? super BaseResponse> subscriber) {
                handleActiveUserResponse(mRemoteApiInterface.activeUser(userId, authCode), subscriber);
            }
        });
    }

    private void handleSendActiveResponse(Call<BaseResponse<SendActiveEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<SendActiveEntity> response = call.execute().body();
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
    public Observable<BaseResponse<SendActiveEntity>> sendActive(final String userId) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<SendActiveEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<SendActiveEntity>> subscriber) {
                handleSendActiveResponse(mRemoteApiInterface.sendActive(userId), subscriber);
            }
        });
    }

    private void handleRegisterResponse(Call<BaseResponse<VerificationEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<VerificationEntity> response = call.execute().body();
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
    public Observable<BaseResponse<VerificationEntity>> register(final String username, final String password, final String phone) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<VerificationEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<VerificationEntity>> subscriber) {
                handleRegisterResponse(mRemoteApiInterface.register(username, password, phone), subscriber);
            }
        });
    }
}
