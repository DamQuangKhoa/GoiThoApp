package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.ListBookingEntity;
import com.demo.architect.data.repository.order.remote.OrderRepository;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 12/04/2018.
 */
public class BookingUsecase extends BaseUseCase {
    private static final String TAG = BookingUsecase.class.getSimpleName();
    private final OrderRepository remoteRepository;

    public BookingUsecase(OrderRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        String userId = ((RequestValue) requestValues).userId;
        String contentFix = ((RequestValue) requestValues).contentFix;
        String dateFix = ((RequestValue) requestValues).dateFix;
        String saleId = ((RequestValue) requestValues).saleId;
        String addressFix = ((RequestValue) requestValues).addressFix;
        String phoneFix = ((RequestValue) requestValues).phoneFix;
        String nameFix = ((RequestValue) requestValues).nameFix;
        return remoteRepository.setCalendar(userId, contentFix, dateFix, saleId, addressFix, phoneFix, nameFix);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<ListBookingEntity>>() {
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
            public void onNext(BaseResponse<ListBookingEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    ListBookingEntity result = data.getResponse();
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
        public final String contentFix;
        public final String dateFix;
        public final String saleId;
        public final String addressFix;
        public final String phoneFix;
        public final String nameFix;

        public RequestValue(String userId, String contentFix, String dateFix, String saleId,
                            String addressFix, String phoneFix, String nameFix) {
            this.userId = userId;
            this.contentFix = contentFix;
            this.dateFix = dateFix;
            this.saleId = saleId;
            this.addressFix = addressFix;
            this.phoneFix = phoneFix;
            this.nameFix = nameFix;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private ListBookingEntity entity;

        public ResponseValue(ListBookingEntity entity) {
            this.entity = entity;
        }

        public ListBookingEntity getEntity() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
