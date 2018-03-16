package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.ActionEntity;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.repository.action.product.remote.ActionProductRepository;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 08/01/2018.
 */

public class GetListActionProductUsecase extends BaseUseCase {
    private static final String TAG = GetListActionProductUsecase.class.getSimpleName();
    private final ActionProductRepository remoteRepository;

    public GetListActionProductUsecase(ActionProductRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return remoteRepository.findListActionProduct(((RequestValue) requestValues).token);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<List<ActionEntity>>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
                if (useCaseCallback != null) {
                    useCaseCallback.onError(new GetListActionProductUsecase.ErrorValue());
                }
            }

            @Override
            public void onNext(BaseResponse<List<ActionEntity>> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    List<ActionEntity> result = data.getResultObject();
                    if (result != null) {
                        useCaseCallback.onSuccess(new GetListActionProductUsecase.ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new GetListActionProductUsecase.ErrorValue());
                    }
                }
            }
        };
    }

    public static final class RequestValue implements RequestValues {

        private String token;

        public RequestValue(String token) {
            this.token = token;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private final List<ActionEntity> listActionProduct;

        public ResponseValue(List<ActionEntity> listActionProduct) {
            this.listActionProduct = listActionProduct;
        }

        public List<ActionEntity> getListActionProduct() {
            return listActionProduct;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
