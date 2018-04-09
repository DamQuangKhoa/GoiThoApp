package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.VerificationEntity;
import com.demo.architect.data.repository.profile.remote.ProfileRepository;

import rx.Observable;
import rx.Subscriber;


public class ResetPhoneNumberUsecase extends BaseUseCase {
    private static final String TAG = ResetPhoneNumberUsecase.class.getSimpleName();
    private final ProfileRepository remoteRepository;

    public ResetPhoneNumberUsecase(ProfileRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        String username = ((RequestValue) requestValues).username;
        String password = ((RequestValue) requestValues).password;
        String phone = ((RequestValue) requestValues).phone;
        return remoteRepository.resetPhoneNumber(username,password,  phone);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<VerificationEntity>>() {
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
            public void onNext(BaseResponse<VerificationEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    VerificationEntity result = data.getResponse();
                    if (result != null) {
                        useCaseCallback.onSuccess(new ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new ErrorValue());
                    }
                }
            }
        };
    }

    public static final class RequestValue implements RequestValues {
        public final String username;
        public final String password;
        public final String phone;

        public RequestValue(String username, String password, String phone) {
            this.username = username;
            this.password = password;
            this.phone = phone;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private VerificationEntity entity;

        public ResponseValue(VerificationEntity entity) {
            this.entity = entity;
        }

        public VerificationEntity getEntity() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
