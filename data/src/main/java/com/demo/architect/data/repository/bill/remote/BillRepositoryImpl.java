package com.demo.architect.data.repository.bill.remote;

import com.demo.architect.data.model.IMDBEntity;
import com.demo.architect.data.repository.base.remote.RemoteRepositoryImpl;
import com.demo.architect.data.repository.base.remote.RemoteApiInterface;

import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by admin on 7/10/17.
 */

public class BillRepositoryImpl extends RemoteRepositoryImpl implements BillRepository{

    private final BillApiInterface mBillApiInterface;

    public BillRepositoryImpl(RemoteApiInterface mRemoteApiInterface, BillApiInterface mBillApiInterface) {
        super(mRemoteApiInterface);
        this.mBillApiInterface = mBillApiInterface;
    }

    private void handleIMDBEntityResponse(Call<IMDBEntity> call, Subscriber subscriber) {
        try {
            IMDBEntity response = call.execute().body();
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
    public Observable<IMDBEntity> getIMDB(final String url) {
        return Observable.create(new Observable.OnSubscribe<IMDBEntity>() {
            @Override
            public void call(Subscriber<? super IMDBEntity> subscriber) {
                handleIMDBEntityResponse(mBillApiInterface.getIMDB(url), subscriber);
            }
        });
    }
}
