package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.ActivityEntity;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.repository.activity.remote.ActivityRepository;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 07/01/2018.
 */

public class GetListActivityUsecase extends BaseUseCase {
    private static final String TAG = GetListActivityUsecase.class.getSimpleName();
    private final ActivityRepository remoteRepository;

    public GetListActivityUsecase(ActivityRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return remoteRepository.findActivity(((RequestValue) requestValues).token,
                ((RequestValue) requestValues).farmerId);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<List<ActivityEntity>>>() {
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
            public void onNext(BaseResponse<List<ActivityEntity>> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    List<ActivityEntity> result = data.getResultObject();
                    if (result != null && result.size() >= 0) {
                        useCaseCallback.onSuccess(new ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new ErrorValue());
                    }
                }
            }
        };
    }

    public static final class RequestValue implements RequestValues {
        private String token;
        private String farmerId;

        public RequestValue(String token, String farmerId) {
            this.farmerId = farmerId;
            this.token = token;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private final List<ActivityEntity> entities;

        public ResponseValue(List<ActivityEntity> entities) {
            this.entities = entities;
        }

        public List<ActivityEntity> getActivityEntities() {
            return entities;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}

