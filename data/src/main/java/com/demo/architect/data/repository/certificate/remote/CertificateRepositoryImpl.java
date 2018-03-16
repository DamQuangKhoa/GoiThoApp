package com.demo.architect.data.repository.certificate.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.CertificateEntity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

import static com.demo.architect.data.helper.FilterStringHelper.getFilterString;
import static com.demo.architect.data.helper.FilterStringHelper.mergeFilterStrings;

/**
 * Created by Skull on 14/01/2018.
 */

public class CertificateRepositoryImpl implements CertificateRepository{
    private final static String TAG = CertificateRepositoryImpl.class.getName();

    private CertificateApiInterface mRemoteApiInterface;

    public CertificateRepositoryImpl(CertificateApiInterface mRemoteApiInterface) {
        this.mRemoteApiInterface = mRemoteApiInterface;
    }

    @Override
    public Observable<BaseResponse<List<CertificateEntity>>> findListCertificateByFarmerId(final String token, final String filter) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<List<CertificateEntity>>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<List<CertificateEntity>>> subscriber) {
                String filterString = getFilterString("farmer_id", filter);
                handleGetListCertificateResponse(mRemoteApiInterface.findListCertificateByFarmerId(token, filterString), subscriber);
            }
        });
    }

    private void handleGetListCertificateResponse(Call<BaseResponse<List<CertificateEntity>>> call, Subscriber subscriber) {
        try {
            BaseResponse<List<CertificateEntity>> response = call.execute().body();
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
    public Observable<BaseResponse<CertificateEntity>> updateCertificate(final String token, final String id, final HashMap<String, String> fields) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<CertificateEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<CertificateEntity>> subscriber) {
                JSONObject json = new JSONObject(fields);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json.toString());
                handleUpdateCertificateResponse(mRemoteApiInterface.updateCertificate(token, id, body), subscriber);
            }
        });
    }

    private void handleUpdateCertificateResponse(Call<BaseResponse<CertificateEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<CertificateEntity> response = call.clone().execute().body();
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
    public Observable<BaseResponse<CertificateEntity>> createCertificate(final String token, final HashMap<String, String> fields) {
        return Observable.create(new Observable.OnSubscribe<BaseResponse<CertificateEntity>>() {
            @Override
            public void call(Subscriber<? super BaseResponse<CertificateEntity>> subscriber) {
                JSONObject json = new JSONObject(fields);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json.toString());
                handleCreateCertificateResponse(mRemoteApiInterface.createCertificate(token, body), subscriber);
            }
        });
    }

    private void handleCreateCertificateResponse(Call<BaseResponse<CertificateEntity>> call, Subscriber subscriber) {
        try {
            BaseResponse<CertificateEntity> response = call.clone().execute().body();
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
