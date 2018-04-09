package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.ResetPasswordEntity;
import com.demo.architect.data.repository.auth.remote.AuthRepository;
import com.demo.architect.data.repository.profile.remote.ProfileRepository;

import rx.Observable;
import rx.Subscriber;


public class ResetPasswordUsecase extends BaseUseCase {
    private static final String TAG = ResetPasswordUsecase.class.getSimpleName();
    private final ProfileRepository remoteRepository;

    public ResetPasswordUsecase(ProfileRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        String email = ((RequestValue) requestValues).email;
        String phone = ((RequestValue) requestValues).phone;
        return remoteRepository.resetPassword(email, phone);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<ResetPasswordEntity>>() {
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
            public void onNext(BaseResponse<ResetPasswordEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    ResetPasswordEntity result = data.getResponse();
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
        public final String email;
        public final String phone;

        public RequestValue(String email, String phone) {
            this.email = email;
            this.phone = phone;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private ResetPasswordEntity entity;

        public ResponseValue(ResetPasswordEntity entity) {
            this.entity = entity;
        }

        public ResetPasswordEntity getEntity() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
