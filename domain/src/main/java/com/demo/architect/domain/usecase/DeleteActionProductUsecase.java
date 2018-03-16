package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.ActionEntity;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.repository.action.product.remote.ActionProductRepository;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 12/01/2018.
 */

public class DeleteActionProductUsecase extends BaseUseCase {
    private static final String TAG = DeleteActionProductUsecase.class.getSimpleName();
    private final ActionProductRepository remoteRepository;

    public DeleteActionProductUsecase(ActionProductRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return remoteRepository.deleteActionProduct(((DeleteActionProductUsecase.RequestValue) requestValues).token,
                ((DeleteActionProductUsecase.RequestValue) requestValues).id);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<ActionEntity>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
                if (useCaseCallback != null) {
                    useCaseCallback.onError(new DeleteActionProductUsecase.ErrorValue());
                }
            }

            @Override
            public void onNext(BaseResponse<ActionEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    ActionEntity result = data.getResultObject();
                    if (result != null ) {
                        useCaseCallback.onSuccess(new DeleteActionProductUsecase.ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new DeleteActionProductUsecase.ErrorValue());
                    }
                }
            }
        };
    }

    public static final class RequestValue implements RequestValues {
        private String token;
        private String id;

        public RequestValue(String token, String id) {
            this.id = id;
            this.token = token;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private final ActionEntity entity;

        public ResponseValue(ActionEntity entity) {
            this.entity = entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}

