package com.demo.architect.data.repository.profile.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.PointEntity;
import com.demo.architect.data.model.ResetPasswordEntity;
import com.demo.architect.data.model.UpdateUserEntity;
import com.demo.architect.data.model.UserEntity;
import com.demo.architect.data.model.VerificationEntity;

import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 04/01/2018.
 */

public class ProfileRepositoryImpl implements ProfileRepository {
    private final static String TAG = ProfileRepositoryImpl.class.getName();

    private ProfileApiInterface mRemoteApiInterface;

    public ProfileRepositoryImpl(ProfileApiInterface mRemoteApiInterface) {
        this.mRemoteApiInterface = mRemoteApiInterface;
    }


    private void handleUpdateUserResponse(Call<BaseResponse<UpdateUserEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<UpdateUserEntity> response = call.execute().body();
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

    private void handleResetPasswordResponse(Call<BaseResponse<ResetPasswordEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<ResetPasswordEntity> response = call.execute().body();
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

    private void handleResetPhoneNumberResponse(Call<BaseResponse<VerificationEntity>> call, Subscriber subscriber) {
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

    private void handleActiveResetPasswordResponse(Call<BaseResponse> call, Subscriber subscriber) {
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

    private void handleActiveResetPhoneNumberResponse(Call<BaseResponse> call, Subscriber subscriber) {
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


    private void handlePointResponse(Call<BaseResponse<PointEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<PointEntity> response = call.execute().body();
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
    public Observable<BaseResponse<ResetPasswordEntity>> resetPassword(final String username, final String phone) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<ResetPasswordEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<ResetPasswordEntity>> subscriber) {
                handleResetPasswordResponse(mRemoteApiInterface.resetPassword(username, phone), subscriber);
            }
        });
    }

    @Override
    public Observable<BaseResponse<VerificationEntity>> resetPhoneNumber(final String username, final String password, final String phone) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<VerificationEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<VerificationEntity>> subscriber) {
                handleResetPhoneNumberResponse(mRemoteApiInterface.resetPhoneNumber(username, password, phone), subscriber);
            }
        });
    }

    @Override
    public Observable<BaseResponse> activeResetPassword(final String userId, final String authCode, final String newPassword) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse>() {
            @Override
            public void call(Subscriber<? super BaseResponse> subscriber) {
                handleActiveResetPasswordResponse(mRemoteApiInterface.activeResetPassword(userId, authCode, newPassword), subscriber);
            }
        });
    }

    @Override
    public Observable<BaseResponse> activeResetPhoneNumber(final String userId, final String authCode, final String newPhoneNumber) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse>() {
            @Override
            public void call(Subscriber<? super BaseResponse> subscriber) {
                handleActiveResetPhoneNumberResponse(mRemoteApiInterface.activeResetPassword(userId, authCode, newPhoneNumber), subscriber);
            }
        });
    }

    @Override
    public Observable<BaseResponse<UpdateUserEntity>> updateUserProfile(final UserEntity entity) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<UpdateUserEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<UpdateUserEntity>> subscriber) {
                handleUpdateUserResponse(mRemoteApiInterface.updateUserInfo(entity.getUserId(),
                        entity.getUserFullName(),
                        entity.getAvatarImageUrl(),
                        entity.getAddress1(),
                        entity.getAddress2(),
                        entity.getEmail(),
                        entity.getMobilePhone()), subscriber);
            }
        });
    }

    @Override
    public Observable<BaseResponse<PointEntity>> getInfoPoint(final String userId) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<PointEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<PointEntity>> subscriber) {
                handlePointResponse(mRemoteApiInterface.getInfoPoint(userId),subscriber);
            }
        });
    }
}
