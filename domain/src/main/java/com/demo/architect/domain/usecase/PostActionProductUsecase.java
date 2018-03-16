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

public class PostActionProductUsecase extends BaseUseCase {
    private static final String TAG = PostActionProductUsecase.class.getSimpleName();
    private final ActionProductRepository remoteRepository;

    public PostActionProductUsecase(ActionProductRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return remoteRepository.createActionProduct(((PostActionProductUsecase.RequestValue) requestValues).token,
                ((PostActionProductUsecase.RequestValue) requestValues).name);
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
                    useCaseCallback.onError(new PostActionProductUsecase.ErrorValue());
                }
            }

            @Override
            public void onNext(BaseResponse<ActionEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    ActionEntity result = data.getResultObject();
                    if (result != null) {
                        useCaseCallback.onSuccess(new PostActionProductUsecase.ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new PostActionProductUsecase.ErrorValue());
                    }
                }
            }
        };
    }

    public static final class RequestValue implements RequestValues {
        private String token;
        private String name;
        public RequestValue(String token, String name) {
            this.token = token;
            this.name = name;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private final ActionEntity entity;

        public ResponseValue(ActionEntity entity) {
            this.entity = entity;
        }

        public ActionEntity getListActionProduct() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
