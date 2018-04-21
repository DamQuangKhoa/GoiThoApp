package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.ListNotificationsEntity;
import com.demo.architect.data.repository.notification.remote.NotificationRepository;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 12/04/2018.
 */
public class GetListNotificationsUsecase extends BaseUseCase {
    private static final String TAG = GetListNotificationsUsecase.class.getSimpleName();
    private final NotificationRepository remoteRepository;

    public GetListNotificationsUsecase(NotificationRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        String userId = ((RequestValue) requestValues).userId;
        int loaded = ((RequestValue) requestValues).loaded;
        int perLoad = ((RequestValue) requestValues).perLoad;
        return remoteRepository.getListNotifications(userId, loaded, perLoad);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<ListNotificationsEntity>>() {
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
            public void onNext(BaseResponse<ListNotificationsEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    ListNotificationsEntity result = data.getResponse();
                    if (result != null) {
                        useCaseCallback.onSuccess(new ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new ErrorValue());
                    }
                }
            }
        };
    }

    public static final class RequestValue implements RequestValues {
        public final String userId;
        public final int loaded;
        public final int perLoad;

        public RequestValue(String userId, int loaded, int perLoad) {
            this.userId = userId;
            this.loaded = loaded;
            this.perLoad = perLoad;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private ListNotificationsEntity entity;

        public ResponseValue(ListNotificationsEntity entity) {
            this.entity = entity;
        }

        public ListNotificationsEntity getEntity() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
