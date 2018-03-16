package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.CertificateEntity;
import com.demo.architect.data.repository.certificate.remote.CertificateRepository;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 14/01/2018.
 */

public class GetListCertificateByFarmerIdUsecase extends BaseUseCase {
    private static final String TAG = GetListCertificateByFarmerIdUsecase.class.getSimpleName();
    private final CertificateRepository remoteRepository;

    public GetListCertificateByFarmerIdUsecase(CertificateRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        String token = ((RequestValue) requestValues).token;
        String id = ((RequestValue) requestValues).id;
        return remoteRepository.findListCertificateByFarmerId(token, id);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<List<CertificateEntity>>>() {
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
            public void onNext(BaseResponse<List<CertificateEntity>> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    List<CertificateEntity> result = data.getResultObject();
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
        public final String token;
        public final String id;
        public RequestValue(String token, String id) {
            this.token = token;
            this.id = id;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private final List<CertificateEntity> listCertificate;

        public ResponseValue(List<CertificateEntity> listCertificate) {
            this.listCertificate = listCertificate;
        }

        public List<CertificateEntity> getListCertificate() {
            return listCertificate;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
