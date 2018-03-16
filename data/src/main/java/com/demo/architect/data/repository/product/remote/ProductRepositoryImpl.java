package com.demo.architect.data.repository.product.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.ProductEntity;

import java.util.List;

import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 07/01/2018.
 */

public class ProductRepositoryImpl implements ProductRepository{
    private final static String TAG = ProductRepositoryImpl.class.getName();

    private ProductApiInterface mRemoteApiInterface;

    public ProductRepositoryImpl(ProductApiInterface mRemoteApiInterface) {
        this.mRemoteApiInterface = mRemoteApiInterface;
    }

    @Override
    public Observable<BaseResponse<List<ProductEntity>>> findProduct(final String token ) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<List<ProductEntity>>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<List<ProductEntity>>> subscriber) {
                handleFindProductResponse(mRemoteApiInterface.findProduct(token), subscriber);
            }
        });
    }

    private void handleFindProductResponse(Call<BaseResponse<List<ProductEntity>>> call, Subscriber subscriber) {
        try {
            BaseResponse<List<ProductEntity>> response = call.execute().body();
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
