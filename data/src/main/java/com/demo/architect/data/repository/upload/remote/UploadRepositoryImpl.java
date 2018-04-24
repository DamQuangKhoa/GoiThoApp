package com.demo.architect.data.repository.upload.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.UploadEntity;

import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 13/01/2018.
 */

public class UploadRepositoryImpl implements UploadRepository {

    private final static String TAG = UploadRepositoryImpl.class.getName();

    private UploadApiInterface mRemoteApiInterface;

    public UploadRepositoryImpl(UploadApiInterface mRemoteApiInterface) {
        this.mRemoteApiInterface = mRemoteApiInterface;
    }


    private void handleUploadImageResponse(Call<BaseResponse<UploadEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<UploadEntity> response = call.clone().execute().body();

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
    public Observable<BaseResponse<UploadEntity>> uploadImageSetCalendar(final String userId, final String orderId, final String image) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<UploadEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<UploadEntity>> subscriber) {
                handleUploadImageResponse(mRemoteApiInterface.uploadImageSetCalendar(userId, orderId, image), subscriber);
            }
        });
    }
}
