package com.demo.architect.data.repository.action.product.remote;

import com.demo.architect.data.model.ActionEntity;
import com.demo.architect.data.model.BaseResponse;

import java.util.List;

import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 08/01/2018.
 */

public class ActionProductRepositoryImple implements ActionProductRepository{
    private final static String TAG = ActionProductRepositoryImple.class.getName();

    private ActionProductApiInterface mRemoteApiInterface;

    public ActionProductRepositoryImple(ActionProductApiInterface mRemoteApiInterface) {
        this.mRemoteApiInterface = mRemoteApiInterface;
    }

    @Override
    public Observable<BaseResponse<List<ActionEntity>>> findListActionProduct(final String token) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<List<ActionEntity>>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<List<ActionEntity>>> subscriber) {
                handleGetListActionProductResponse(mRemoteApiInterface.findListActionProduct(token), subscriber);
            }
        });
    }

    @Override
    public Observable<BaseResponse<ActionEntity>> createActionProduct(final String token, final String name) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<ActionEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<ActionEntity>> subscriber) {
                handleCreateActionProductResponse(mRemoteApiInterface.createProductAction(token, name), subscriber);
            }


        });
    }

    @Override
    public Observable<BaseResponse<ActionEntity>> deleteActionProduct(final String token, final String id) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<ActionEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<ActionEntity>> subscriber) {
                handleDeleteActionProductResponse(mRemoteApiInterface.deleteActionProduct(token, id), subscriber);
            }
        });
    }

    private void handleGetListActionProductResponse(Call<BaseResponse<List<ActionEntity>>> call, Subscriber subscriber) {
        try {
            BaseResponse<List<ActionEntity>> response = call.execute().body();
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

    private void handleCreateActionProductResponse(Call<BaseResponse<ActionEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<ActionEntity> response = call.execute().body();
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

    private void handleDeleteActionProductResponse(Call<BaseResponse<ActionEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<ActionEntity> response = call.clone().execute().body();
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
