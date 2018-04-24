package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.UploadEntity;
import com.demo.architect.data.repository.upload.remote.UploadRepository;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 12/04/2018.
 */
public class UploadImageSetCalenderUsecase extends BaseUseCase {
    private static final String TAG = UploadImageSetCalenderUsecase.class.getSimpleName();
    private final UploadRepository remoteRepository;

    public UploadImageSetCalenderUsecase(UploadRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        String userId = ((RequestValue) requestValues).userId;
        String orderId = ((RequestValue) requestValues).orderId;
        String image = ((RequestValue) requestValues).image;
        return remoteRepository.uploadImageSetCalendar(userId, orderId, image);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<UploadEntity>>() {
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
            public void onNext(BaseResponse<UploadEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    UploadEntity result = data.getResponse();
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
        public final String orderId;
        public final String image;

        public RequestValue(String userId, String orderId, String image) {
            this.userId = userId;
            this.orderId = orderId;
            this.image = image;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private UploadEntity entity;

        public ResponseValue(UploadEntity entity) {
            this.entity = entity;
        }

        public UploadEntity getEntity() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
