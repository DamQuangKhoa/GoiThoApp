package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.VerificationEntity;
import com.demo.architect.data.repository.auth.remote.AuthRepository;

import rx.Observable;
import rx.Subscriber;


public class RegisterUsecase extends BaseUseCase {
    private static final String TAG = RegisterUsecase.class.getSimpleName();
    private final AuthRepository remoteRepository;

    public RegisterUsecase(AuthRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        String email = ((RequestValue) requestValues).email;
        String password = ((RequestValue) requestValues).password;
        String phone = ((RequestValue) requestValues).phone;
        return remoteRepository.register(email, password, phone);
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
                    useCaseCallback.onError(new ErrorValue(-1));
                }
            }

            @Override
            public void onNext(BaseResponse<VerificationEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    VerificationEntity result = data.getResponse();
                    if (result != null && data.getCode() == 200 || (
                            result != null && data.getCode() == 201 && result.getActive() == 0)) {
                        useCaseCallback.onSuccess(new ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new ErrorValue(data.getCode()));
                    }
                }
            }
        };
    }

    public static final class RequestValue implements RequestValues {
        public final String email;
        public final String password;
        public final String phone;

        public RequestValue(String email, String password, String phone) {
            this.email = email;
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
        public final int errorCode;

        public ErrorValue(int errorCode) {
            this.errorCode = errorCode;
        }

        public int getErrorCode() {
            return errorCode;
        }
    }
}
