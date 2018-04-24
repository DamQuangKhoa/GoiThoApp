package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.SendActiveEntity;
import com.demo.architect.data.repository.auth.remote.AuthRepository;

import rx.Observable;
import rx.Subscriber;


public class SendActiveUsecase extends BaseUseCase {
    private static final String TAG = SendActiveUsecase.class.getSimpleName();
    private final AuthRepository remoteRepository;

    public SendActiveUsecase(AuthRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        String userId = ((RequestValue) requestValues).userId;
        return remoteRepository.sendActive(userId+"");
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<SendActiveEntity>>() {
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
            public void onNext(BaseResponse<SendActiveEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    SendActiveEntity result = data.getResponse();
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
        public final String userId;

        public RequestValue(String userId) {
            this.userId = userId;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private SendActiveEntity entity;

        public ResponseValue(SendActiveEntity entity) {
            this.entity = entity;
        }

        public SendActiveEntity getEntity() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
