package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.ActivityBuyFertilizerEntity;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.repository.buy.fetilizer.activity.remote.ActivityBuyFertilizerRepository;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 14/01/2018.
 */

public class PutActivityBuyFertilizerUsecase extends BaseUseCase {
    private static final String TAG = PutActivityBuyFertilizerUsecase.class.getSimpleName();
    private final ActivityBuyFertilizerRepository remoteRepository;

    public PutActivityBuyFertilizerUsecase(ActivityBuyFertilizerRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return remoteRepository.updateActivity(((PutActivityBuyFertilizerUsecase.RequestValue) requestValues).token,
                ((PutActivityBuyFertilizerUsecase.RequestValue) requestValues).id,
                ((PutActivityBuyFertilizerUsecase.RequestValue) requestValues).getRequestValue());
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
                    useCaseCallback.onError(new PutActivityBuyFertilizerUsecase.ErrorValue());
                }
            }

            @Override
            public void onNext(BaseResponse<ActivityBuyFertilizerEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    ActivityBuyFertilizerEntity result = data.getResultObject();
                    if (result != null ) {
                        useCaseCallback.onSuccess(new PutActivityBuyFertilizerUsecase.ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new PutActivityBuyFertilizerUsecase.ErrorValue());
                    }
                }
            }
        };
    }

    public static final class RequestValue implements RequestValues {
        private String token;
        private String id;
        private ActivityBuyFertilizerEntity entity;

        public RequestValue(String token, String id, ActivityBuyFertilizerEntity entity) {
            this.id = id;
            this.token = token;
            this.entity = entity;
        }

        public HashMap<String, String> getRequestValue() {
            HashMap<String, String> request = generateHashmapFromEntity(entity, token);
            return request;
        }

        public HashMap<String, String> generateHashmapFromEntity(ActivityBuyFertilizerEntity request, String accessToken) {
            HashMap<String, String> map = new HashMap<>();
            map.put("activity_id", (request.getActivityId()));
            map.put("fertilizer", (request.getFertilizer()));
            map.put("fertilizer_id", (request.getFertilizerId()));
            map.put("quality", (request.getQuality()));
            map.put("company", (request.getCompany()));
            map.put("store", request.getStore());
            map.put("exp_date", (request.getExpDate()));
            map.put("quantity", (request.getQuantity().toString()));
            return map;
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

