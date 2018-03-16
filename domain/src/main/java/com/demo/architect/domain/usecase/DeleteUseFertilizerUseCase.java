package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.ActivityUseFertilizerEntity;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.repository.use.fertilizer.activity.remote.ActivityUseFertilizerRepository;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 21/01/2018.
 */

public class DeleteUseFertilizerUseCase extends BaseUseCase {
    private static final String TAG = DeleteUseFertilizerUseCase.class.getSimpleName();
    private final ActivityUseFertilizerRepository remoteRepository;

    public DeleteUseFertilizerUseCase(ActivityUseFertilizerRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return remoteRepository.deleteActivity(((DeleteUseFertilizerUseCase.RequestValue) requestValues).token,
                ((DeleteUseFertilizerUseCase.RequestValue) requestValues).id);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<ActivityUseFertilizerEntity>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
                if (useCaseCallback != null) {
                    useCaseCallback.onError(new DeleteUseFertilizerUseCase.ErrorValue());
                }
            }

            @Override
            public void onNext(BaseResponse<ActivityUseFertilizerEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    ActivityUseFertilizerEntity result = data.getResultObject();
                    if (result != null ) {
                        useCaseCallback.onSuccess(new DeleteUseFertilizerUseCase.ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new DeleteUseFertilizerUseCase.ErrorValue());
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
        private final ActivityUseFertilizerEntity entity;

        public ResponseValue(ActivityUseFertilizerEntity entity) {
            this.entity = entity;
        }

        public ActivityUseFertilizerEntity getActivityEntities() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}

