package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.SaleEntity;
import com.demo.architect.data.repository.order.remote.OrderRepository;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 12/04/2018.
 */
public class CheckSaleUsecase extends BaseUseCase {
    private static final String TAG = CheckSaleUsecase.class.getSimpleName();
    private final OrderRepository remoteRepository;

    public CheckSaleUsecase(OrderRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        String saleId = ((RequestValue) requestValues).saleId;
        return remoteRepository.checkSaleId(saleId);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<SaleEntity>>() {
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
            public void onNext(BaseResponse<SaleEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    SaleEntity result = data.getResponse();
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
        public final String saleId;
        public RequestValue(String saleId) {
            this.saleId = saleId;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private SaleEntity entity;

        public ResponseValue(SaleEntity entity) {
            this.entity = entity;
        }

        public SaleEntity getEntity() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
