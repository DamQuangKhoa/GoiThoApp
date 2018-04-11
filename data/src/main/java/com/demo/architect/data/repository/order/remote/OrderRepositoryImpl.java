package com.demo.architect.data.repository.order.remote;

import com.demo.architect.data.model.BaseResponse;

import retrofit2.Call;
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


    private void handleBaseResponse(Call<BaseResponse> call, Subscriber subscriber) {
        try {
            BaseResponse response = call.execute().body();
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


}
