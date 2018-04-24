package com.demo.architect.data.repository.order.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.ListBookingEntity;
import com.demo.architect.data.model.SaleEntity;

import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 04/01/2018.
 */

public class OrderRepositoryImpl implements OrderRepository {
    private final static String TAG = OrderRepositoryImpl.class.getName();

    private OrderApiInterface mRemoteApiInterface;

    public OrderRepositoryImpl(OrderApiInterface mRemoteApiInterface) {
        this.mRemoteApiInterface = mRemoteApiInterface;
    }


    private void handleBookingResponse(Call<BaseResponse<ListBookingEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<ListBookingEntity> response = call.execute().body();
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

    private void handleSaleResponse(Call<BaseResponse<SaleEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<SaleEntity> response = call.execute().body();
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
    public Observable<BaseResponse<ListBookingEntity>> setCalendar(final String userId, final String contentFix,
                                                                   final String dateFix, final String saleId,
                                                                   final String addressFix, final String phoneFix,
                                                                   final String nameFix) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<ListBookingEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<ListBookingEntity>> subscriber) {
                handleBookingResponse(mRemoteApiInterface.setCalendar(userId, contentFix, dateFix,
                        saleId, addressFix, phoneFix, nameFix), subscriber);
            }
        });
    }

    @Override
    public Observable<BaseResponse<SaleEntity>> checkSaleId(final String saleId) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<SaleEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<SaleEntity>> subscriber) {
                handleSaleResponse(mRemoteApiInterface.checkSaleId(saleId), subscriber);
            }
        });
    }
}
