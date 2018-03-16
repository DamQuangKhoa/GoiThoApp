package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.ActivityUseFertilizerEntity;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.repository.use.fertilizer.activity.remote.ActivityUseFertilizerRepository;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 14/01/2018.
 */

public class PostActivityUseFertilizerUsecase extends BaseUseCase {
    private static final String TAG = PostActivityUseFertilizerUsecase.class.getSimpleName();
    private final ActivityUseFertilizerRepository remoteRepository;

    public PostActivityUseFertilizerUsecase(ActivityUseFertilizerRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return remoteRepository.createActivity(((PostActivityUseFertilizerUsecase.RequestValue) requestValues).token,
                ((PostActivityUseFertilizerUsecase.RequestValue) requestValues).getRequestValue());
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
                    useCaseCallback.onError(new PostActivityUseFertilizerUsecase.ErrorValue());
                }
            }

            @Override
            public void onNext(BaseResponse<ActivityUseFertilizerEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    ActivityUseFertilizerEntity result = data.getResultObject();
                    if (result != null ) {
                        useCaseCallback.onSuccess(new PostActivityUseFertilizerUsecase.ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new PostActivityUseFertilizerUsecase.ErrorValue());
                    }
                }
            }
        };
    }

    public static final class RequestValue implements RequestValues {
        private String token;
        private ActivityUseFertilizerEntity entity;

        public RequestValue(String token, ActivityUseFertilizerEntity entity) {
            this.entity = entity;
            this.token = token;
        }

        public HashMap<String, String> getRequestValue() {
            HashMap<String, String> request = generateHashmapFromEntity(entity, token);
            return request;
        }

        public HashMap<String, String> generateHashmapFromEntity(ActivityUseFertilizerEntity request, String accessToken) {
            HashMap<String, String> map = new HashMap<>();
            map.put("activity_id", (request.getActivityId()));
            map.put("field_id", (request.getFieldId()));
            map.put("field_area", (request.getFieldArea()));
            map.put("fertilizer", (request.getFertilizer()));
            map.put("fertilizer_id", (request.getFertilizerId()));
            map.put("dosage", (request.getDosage().toString()));
            map.put("quantity", (request.getQuantity().toString()));
            map.put("stock", (request.getStock().toString()));
            map.put("goal", (request.getGoal()));
            map.put("equiment", (request.getEquiment()));
            map.put("isolation_time", (request.getIsolationTime().toString()));
            map.put("cleaned_equipment", (request.getCleanedEquipment().toString()));
            return map;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private final ActivityUseFertilizerEntity entity;

        public ResponseValue(ActivityUseFertilizerEntity entity) {
            this.entity = entity;
        }

        public ActivityUseFertilizerEntity getActivityUseFertilizerEntities() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}

