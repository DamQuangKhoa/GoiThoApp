package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.ActivityEntity;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.HistoryActivitiesEntity;
import com.demo.architect.data.repository.activity.remote.ActivityRepository;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 08/01/2018.
 */

public class GetHistoryActivityUsecase extends BaseUseCase {
    private static final String TAG = GetHistoryActivityUsecase.class.getSimpleName();
    private final ActivityRepository remoteRepository;

    public GetHistoryActivityUsecase(ActivityRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return remoteRepository.findHistoryActivityById(((GetHistoryActivityUsecase.RequestValue) requestValues).token,
                ((GetHistoryActivityUsecase.RequestValue) requestValues).id);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<HistoryActivitiesEntity>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
                if (useCaseCallback != null) {
                    useCaseCallback.onError(new GetHistoryActivityUsecase.ErrorValue());
                }
            }

            @Override
            public void onNext(BaseResponse<HistoryActivitiesEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    List<ActivityEntity> result = data.getResultObject().getActivities();
                    if (result != null && result.size() >= 0) {
                        useCaseCallback.onSuccess(new GetHistoryActivityUsecase.ResponseValue(data.getResultObject()));
                    } else {
                        useCaseCallback.onError(new GetHistoryActivityUsecase.ErrorValue());
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
        private final HistoryActivitiesEntity entities;

        public ResponseValue(HistoryActivitiesEntity entities) {
            this.entities = entities;
        }

        public HistoryActivitiesEntity getActivityEntities() {
            return entities;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}

