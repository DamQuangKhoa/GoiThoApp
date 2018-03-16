package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.ProductEntity;
import com.demo.architect.data.repository.product.remote.ProductRepository;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 07/01/2018.
 */

public class GetProductUsecase extends BaseUseCase{
    private static final String TAG = GetProductUsecase.class.getSimpleName();
    private final ProductRepository remoteRepository;

    public GetProductUsecase(ProductRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return remoteRepository.findProduct(((RequestValue) requestValues).token);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<List<ProductEntity>>>() {
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
            public void onNext(BaseResponse<List<ProductEntity>> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    List<ProductEntity> result = data.getResultObject();
                    if (result != null) {
                        useCaseCallback.onSuccess(new ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new ErrorValue());
                    }
                }
            }
        };
    }

    public static final class RequestValue implements BaseUseCase.RequestValues {

        private String token;

        public RequestValue(String token) {
            this.token = token;
        }
    }

    public static final class ResponseValue implements BaseUseCase.ResponseValues {
        private final List<ProductEntity> entity;

        public ResponseValue(List<ProductEntity> entity) {
            this.entity = entity;
        }

        public List<ProductEntity> getProductEntity() {
            return entity;
        }
    }

    public static final class ErrorValue implements BaseUseCase.ErrorValues {
    }
}
