package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.ActivityEntity;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.repository.activity.remote.ActivityRepository;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 11/01/2018.
 */

public class DeleteActivityUsecase extends BaseUseCase {
    private static final String TAG = DeleteActivityUsecase.class.getSimpleName();
    private final ActivityRepository remoteRepository;

    public DeleteActivityUsecase(ActivityRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return remoteRepository.deleteActivity(((DeleteActivityUsecase.RequestValue) requestValues).token,
                ((DeleteActivityUsecase.RequestValue) requestValues).id);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<ActivityEntity>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
                if (useCaseCallback != null) {
                    useCaseCallback.onError(new DeleteActivityUsecase.ErrorValue());
                }
            }

            @Override
            public void onNext(BaseResponse<ActivityEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    ActivityEntity result = data.getResultObject();
                    if (result != null ) {
                        useCaseCallback.onSuccess(new DeleteActivityUsecase.ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new DeleteActivityUsecase.ErrorValue());
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
        private final ActivityEntity entity;

        public ResponseValue(ActivityEntity entity) {
            this.entity = entity;
        }

        public ActivityEntity getActivityEntities() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}

