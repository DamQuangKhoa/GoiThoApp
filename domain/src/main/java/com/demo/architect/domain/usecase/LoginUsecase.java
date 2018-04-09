package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.UserEntity;
import com.demo.architect.data.repository.auth.remote.AuthRepository;

import rx.Observable;
import rx.Subscriber;


public class LoginUsecase extends BaseUseCase {
    private static final String TAG = LoginUsecase.class.getSimpleName();
    private final AuthRepository remoteRepository;

    public LoginUsecase(AuthRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        String email = ((RequestValue) requestValues).email;
        String password = ((RequestValue) requestValues).password;
        return remoteRepository.login(email, password);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<UserEntity>>() {
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
            public void onNext(BaseResponse<UserEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    UserEntity result = data.getResponse();
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
        public final String password;

        public RequestValue(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private UserEntity entity;

        public ResponseValue(UserEntity entity) {
            this.entity = entity;
        }

        public UserEntity getEntity() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
