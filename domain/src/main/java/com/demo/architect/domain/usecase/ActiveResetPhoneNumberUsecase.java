package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.repository.profile.remote.ProfileRepository;

import rx.Observable;
import rx.Subscriber;


public class ActiveResetPhoneNumberUsecase extends BaseUseCase {
    private static final String TAG = ActiveResetPhoneNumberUsecase.class.getSimpleName();
    private final ProfileRepository remoteRepository;

    public ActiveResetPhoneNumberUsecase(ProfileRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        int userId = ((RequestValue) requestValues).userId;
        String authCode = ((RequestValue) requestValues).authCode;
        String newPhoneNumber = ((RequestValue) requestValues).newPhoneNumber;
        return remoteRepository.activeResetPhoneNumber(userId, authCode, newPhoneNumber);
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
        public final int userId;
        public final String authCode;
        public final String newPhoneNumber;

        public RequestValue(int userId, String authCode, String newPhoneNumber) {
            this.userId = userId;
            this.authCode = authCode;
            this.newPhoneNumber = newPhoneNumber;
        }
    }

    public static final class ResponseValue implements ResponseValues {
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
