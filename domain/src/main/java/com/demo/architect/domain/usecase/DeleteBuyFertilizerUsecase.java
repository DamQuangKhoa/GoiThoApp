package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.ActivityBuyFertilizerEntity;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.repository.buy.fetilizer.activity.remote.ActivityBuyFertilizerRepository;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 21/01/2018.
 */

public class DeleteBuyFertilizerUsecase extends BaseUseCase {
    private static final String TAG = DeleteBuyFertilizerUsecase.class.getSimpleName();
    private final ActivityBuyFertilizerRepository remoteRepository;

    public DeleteBuyFertilizerUsecase(ActivityBuyFertilizerRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return remoteRepository.deleteActivity(((DeleteBuyFertilizerUsecase.RequestValue) requestValues).token,
                ((DeleteBuyFertilizerUsecase.RequestValue) requestValues).id);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<ActivityBuyFertilizerEntity>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
                if (useCaseCallback != null) {
                    useCaseCallback.onError(new DeleteBuyFertilizerUsecase.ErrorValue());
                }
            }

            @Override
            public void onNext(BaseResponse<ActivityBuyFertilizerEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    ActivityBuyFertilizerEntity result = data.getResultObject();
                    if (result != null ) {
                        useCaseCallback.onSuccess(new DeleteBuyFertilizerUsecase.ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new DeleteBuyFertilizerUsecase.ErrorValue());
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
        private final ActivityBuyFertilizerEntity entity;

        public ResponseValue(ActivityBuyFertilizerEntity entity) {
            this.entity = entity;
        }

        public ActivityBuyFertilizerEntity getActivityEntities() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}

