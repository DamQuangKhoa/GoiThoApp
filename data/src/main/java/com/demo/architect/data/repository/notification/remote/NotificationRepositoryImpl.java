package com.demo.architect.data.repository.notification.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.ListNotificationsEntity;
import com.demo.architect.data.model.ListPromotionsEntity;
import com.demo.architect.data.model.NotificationEntity;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 04/01/2018.
 */

public class NotificationRepositoryImpl implements NotificationRepository {
    private final static String TAG = NotificationRepositoryImpl.class.getName();

    private NotificationApiInterface mRemoteApiInterface;

    public NotificationRepositoryImpl(NotificationApiInterface mRemoteApiInterface) {
        this.mRemoteApiInterface = mRemoteApiInterface;
    }



    private void handleGetListPromotionsResponse(Call<BaseResponse<ListPromotionsEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<ListPromotionsEntity> response = call.execute().body();
            if (!subscriber.isUnsubscribed()) {
                if (response != null) {
                    subscriber.onNext(response);
                } else {
                    subscriber.onError(new Exception("Network Error!"));
                }
                subscriber.onCompleted();
            }
        } catch (Exception e) {
            if (!subscriber.isUnsubscribed()) {
                subscriber.onError(e);
                subscriber.onCompleted();
            }
        }
    }

    private void handleGetListNotificationsResponse(Call<BaseResponse<ListNotificationsEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<ListNotificationsEntity> response = call.execute().body();
            if (!subscriber.isUnsubscribed()) {
                if (response != null) {
                    subscriber.onNext(response);
                } else {
                    subscriber.onError(new Exception("Network Error!"));
                }
                subscriber.onCompleted();
            }
        } catch (Exception e) {
            if (!subscriber.isUnsubscribed()) {
                subscriber.onError(e);
                subscriber.onCompleted();
            }
        }
    }

    private void handleGetNewNotificationsResponse(Call<BaseResponse<List<NotificationEntity>>> call, Subscriber subscriber) {
        try {
            BaseResponse<List<NotificationEntity>> response = call.execute().body();
            if (!subscriber.isUnsubscribed()) {
                if (response != null) {
                    subscriber.onNext(response);
                } else {
                    subscriber.onError(new Exception("Network Error!"));
                }
                subscriber.onCompleted();
            }
        } catch (Exception e) {
            if (!subscriber.isUnsubscribed()) {
                subscriber.onError(e);
                subscriber.onCompleted();
            }
        }
    }


    @Override
    public Observable<BaseResponse<ListPromotionsEntity>> getListPromotions(final int loaded, final int perLoad) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<ListPromotionsEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<ListPromotionsEntity>> subscriber) {
                handleGetListPromotionsResponse(mRemoteApiInterface.getListPromotions(loaded, perLoad), subscriber);
            }
        });
    }

    @Override
    public Observable<BaseResponse<ListPromotionsEntity>> getListPromotionsWithUserId(final String userId,
                                                                                      final int loaded,
                                                                                      final int perLoad) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<ListPromotionsEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<ListPromotionsEntity>> subscriber) {

                RequestBody body = RequestBody.create(okhttp3.MediaType
                                                      .parse("application/json; charset=utf-8"),
                                "{\"userId\":\""+userId+"\",\"loaded\":\""+loaded+"\",\"" +
                                             "perload\":\"" +perLoad+"\"}");
                handleGetListPromotionsResponse(mRemoteApiInterface.getListPromotionsWithUserId(body), subscriber);
            }
        });
    }

    @Override
    public Observable<BaseResponse<ListNotificationsEntity>> getListNotifications(final String userId,
                                                                                   final int loaded,
                                                                                   final int perLoad) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<ListNotificationsEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<ListNotificationsEntity>> subscriber) {
                handleGetListNotificationsResponse(mRemoteApiInterface.getListNotifications(userId, loaded, perLoad), subscriber);
            }
        });
    }

    @Override
    public Observable<BaseResponse<List<NotificationEntity>>> getNewNotifications(final String userId) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<List<NotificationEntity>>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<List<NotificationEntity>>> subscriber) {
                handleGetNewNotificationsResponse(mRemoteApiInterface.getNewNotifications(userId), subscriber);
            }
        });
    }


}
