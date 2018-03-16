package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.FarmerEntity;
import com.demo.architect.data.repository.farmer.remote.FarmerRepository;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 29/01/2018.
 */

public class PutFarmerUsecase extends BaseUseCase {
    private static final String TAG = PutFarmerUsecase.class.getSimpleName();
    private final FarmerRepository remoteRepository;

    public PutFarmerUsecase(FarmerRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return remoteRepository.updateFarmer(((PutFarmerUsecase.RequestValue) requestValues).token,
                ((PutFarmerUsecase.RequestValue) requestValues).id,
                ((PutFarmerUsecase.RequestValue) requestValues).getRequestValue());
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<FarmerEntity>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
                if (useCaseCallback != null) {
                    useCaseCallback.onError(new PutFarmerUsecase.ErrorValue());
                }
            }

            @Override
            public void onNext(BaseResponse<FarmerEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    FarmerEntity result = data.getResultObject();
                    if (result != null ) {
                        useCaseCallback.onSuccess(new PutFarmerUsecase.ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new PutFarmerUsecase.ErrorValue());
                    }
                }
            }
        };
    }

    public static final class RequestValue implements RequestValues {
        private String token;
        private String id;
        private FarmerEntity entity;

        public RequestValue(String token, String id, FarmerEntity entity) {
            this.id = id;
            this.token = token;
            this.entity = entity;
        }

        public HashMap<String, String> getRequestValue() {
            HashMap<String, String> request = generateHashmapFromEntity(entity, token);
            return request;
        }

        public HashMap<String, String> generateHashmapFromEntity(FarmerEntity request, String accessToken) {
            HashMap<String, String> map = new HashMap<>();
//            map.put("activity_id", (request.getActivityId()));
//            map.put("fertilizer", (request.getFertilizer()));
//            map.put("fertilizer_id", (request.getFertilizerId()));
//            map.put("quality", (request.getQuality()));
//            map.put("company", (request.getCompany()));
//            map.put("store", request.getStore());
//            map.put("exp_date", (request.getExpDate()));
//            map.put("quantity", (request.getQuantity().toString()));
            map.put("avatar", request.getAvatar());
            return map;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private final FarmerEntity entity;

        public ResponseValue(FarmerEntity entity) {
            this.entity = entity;
        }

        public FarmerEntity getActivityEntities() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}

