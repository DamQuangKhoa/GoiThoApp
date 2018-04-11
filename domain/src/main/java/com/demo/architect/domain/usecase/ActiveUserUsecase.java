package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.repository.auth.remote.AuthRepository;

import rx.Observable;
import rx.Subscriber;


public class ActiveUserUsecase extends BaseUseCase {
    private static final String TAG = ActiveUserUsecase.class.getSimpleName();
    private final AuthRepository remoteRepository;

    public ActiveUserUsecase(AuthRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        int userId = ((RequestValue) requestValues).userId;
        String authCode = ((RequestValue) requestValues).authCode;
        return remoteRepository.activeUser(userId+"", authCode);
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
                    String result = data.getDescription();
                    if (data.getCode() == 200 || data.getCode() == 202) {
                        useCaseCallback.onSuccess(new ResponseValue(result));
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

        public RequestValue(int userId, String authCode) {
            this.userId = userId;
            this.authCode = authCode;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private String description;

        public ResponseValue(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
