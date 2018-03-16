package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.ActivityEntity;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.repository.activity.remote.ActivityRepository;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 11/01/2018.
 */

public class PostActivityUsecase extends BaseUseCase {
    private static final String TAG = PostActivityUsecase.class.getSimpleName();
    private final ActivityRepository remoteRepository;

    public PostActivityUsecase(ActivityRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return remoteRepository.createActivity(((PostActivityUsecase.RequestValue) requestValues).token,
                ((PostActivityUsecase.RequestValue) requestValues).getRequestValue());
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
                    useCaseCallback.onError(new PostActivityUsecase.ErrorValue());
                }
            }

            @Override
            public void onNext(BaseResponse<ActivityEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    ActivityEntity result = data.getResultObject();
                    if (result != null ) {
                        useCaseCallback.onSuccess(new PostActivityUsecase.ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new PostActivityUsecase.ErrorValue());
                    }
                }
            }
        };
    }

    public static final class RequestValue implements RequestValues {
        private String token;
        private ActivityEntity entity;

        public RequestValue(String token, ActivityEntity entity) {
            this.entity = entity;
            this.token = token;
        }

        public HashMap<String, String> getRequestValue() {
            HashMap<String, String> request = generateHashmapFromEntity(entity, token);
            return request;
        }

        public HashMap<String, String> generateHashmapFromEntity(ActivityEntity request, String accessToken) {
            HashMap<String, String> map = new HashMap<>();
            map.put("farmer_id", (request.getFarmerId()));
            map.put("product_id", (request.getProductId()));
            map.put("product_action_id", (request.getProductActionId()));
            map.put("activity_action_id", (request.getActivityActionId()));
            map.put("assignee_id", (request.getAssigneeId()));
            map.put("note", request.getNote());
            map.put("images", (request.getImages()));
            map.put("date", (request.getDateString()));
            map.put("editor", (request.getEditor()));
            return map;
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

