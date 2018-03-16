package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.EmployeeEntity;
import com.demo.architect.data.repository.employee.remote.EmployeeRepository;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 29/01/2018.
 */

public class PutEmployeeUsecase extends BaseUseCase {
    private static final String TAG = PutEmployeeUsecase.class.getSimpleName();
    private final EmployeeRepository remoteRepository;

    public PutEmployeeUsecase(EmployeeRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return remoteRepository.updateEmployee(((PutEmployeeUsecase.RequestValue) requestValues).token,
                ((PutEmployeeUsecase.RequestValue) requestValues).id,
                ((PutEmployeeUsecase.RequestValue) requestValues).getRequestValue());
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<EmployeeEntity>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
                if (useCaseCallback != null) {
                    useCaseCallback.onError(new PutEmployeeUsecase.ErrorValue());
                }
            }

            @Override
            public void onNext(BaseResponse<EmployeeEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    EmployeeEntity result = data.getResultObject();
                    if (result != null ) {
                        useCaseCallback.onSuccess(new PutEmployeeUsecase.ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new PutEmployeeUsecase.ErrorValue());
                    }
                }
            }
        };
    }

    public static final class RequestValue implements RequestValues {
        private String token;
        private String id;
        private EmployeeEntity entity;

        public RequestValue(String token, String id, EmployeeEntity entity) {
            this.id = id;
            this.token = token;
            this.entity = entity;
        }

        public HashMap<String, String> getRequestValue() {
            HashMap<String, String> request = generateHashmapFromEntity(entity, token);
            return request;
        }

        public HashMap<String, String> generateHashmapFromEntity(EmployeeEntity request, String accessToken) {
            HashMap<String, String> map = new HashMap<>();
//            map.put("activity_id", (request.getActivityId()));
//            map.put("fertilizer", (request.getFertilizer()));
//            map.put("fertilizer_id", (request.getFertilizerId()));
//            map.put("quality", (request.getQuality()));
//            map.put("company", (request.getCompany()));
//            map.put("store", request.getStore());
//            map.put("exp_date", (request.getExpDate()));
//            map.put("quantity", (request.getQuantity().toString()));
            return map;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private final EmployeeEntity entity;

        public ResponseValue(EmployeeEntity entity) {
            this.entity = entity;
        }

        public EmployeeEntity getActivityEntities() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}

