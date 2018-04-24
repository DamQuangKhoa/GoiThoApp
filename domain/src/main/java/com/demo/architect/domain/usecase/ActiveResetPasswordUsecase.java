package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.repository.profile.remote.ProfileRepository;

import rx.Observable;
import rx.Subscriber;


public class ActiveResetPasswordUsecase extends BaseUseCase {
    private static final String TAG = ActiveResetPasswordUsecase.class.getSimpleName();
    private final ProfileRepository remoteRepository;

    public ActiveResetPasswordUsecase(ProfileRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        String userId = ((RequestValue) requestValues).userId;
        String authCode = ((RequestValue) requestValues).authCode;
        String newPassword = ((RequestValue) requestValues).newPassword;
        return remoteRepository.activeResetPassword(userId, authCode, newPassword);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
                if (useCaseCallback != null) {
                    useCaseCallback.onError(new ErrorValue());
                }
            }

            @Override
            public void onNext(BaseResponse data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    if (data != null && data.getCode() == 200) {
                        useCaseCallback.onSuccess(new ResponseValue());
                    } else {
                        useCaseCallback.onError(new ErrorValue());
                    }
                }
            }
        };
    }

    public static final class RequestValue implements RequestValues {
        public final String userId;
        public final String authCode;
        public final String newPassword;

        public RequestValue(String userId, String authCode, String newPassword) {
            this.userId = userId;
            this.authCode = authCode;
            this.newPassword = newPassword;
        }
    }

    public static final class ResponseValue implements ResponseValues {
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
