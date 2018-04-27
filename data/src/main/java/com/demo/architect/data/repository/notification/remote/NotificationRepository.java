package com.demo.architect.data.repository.notification.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.ListNotificationsEntity;
import com.demo.architect.data.model.ListPromotionsEntity;
import com.demo.architect.data.model.NotificationEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Skull on 04/01/2018.
 */

public interface NotificationRepository {
    Observable<BaseResponse<ListPromotionsEntity>> getListPromotions(int loaded, int perLoad);
    Observable<BaseResponse<ListPromotionsEntity>> getListPromotionsWithUserId(String userId, int loaded, int perLoad);

    Observable<BaseResponse<ListNotificationsEntity>> getListNotifications(String userId, int loaded, int perLoad);

    Observable<BaseResponse<List<NotificationEntity>>> getNewNotifications(String userId);

}
