package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.CertificateEntity;
import com.demo.architect.data.repository.certificate.remote.CertificateRepository;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 29/01/2018.
 */

public class PostCertificateUsecase extends BaseUseCase {
    private static final String TAG = PostCertificateUsecase.class.getSimpleName();
    private final CertificateRepository remoteRepository;

    public PostCertificateUsecase(CertificateRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return remoteRepository.createCertificate(((PostCertificateUsecase.RequestValue) requestValues).token,
                ((PostCertificateUsecase.RequestValue) requestValues).getRequestValue());
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<CertificateEntity>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
                if (useCaseCallback != null) {
                    useCaseCallback.onError(new PostCertificateUsecase.ErrorValue());
                }
            }

            @Override
            public void onNext(BaseResponse<CertificateEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    CertificateEntity result = data.getResultObject();
                    if (result != null ) {
                        useCaseCallback.onSuccess(new PostCertificateUsecase.ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new PostCertificateUsecase.ErrorValue());
                    }
                }
            }
        };
    }

    public static final class RequestValue implements RequestValues {
        private String token;
        private CertificateEntity entity;

        public RequestValue(String token, CertificateEntity entity) {
            this.entity = entity;
            this.token = token;
        }

        public HashMap<String, String> getRequestValue() {
            HashMap<String, String> request = generateHashmapFromEntity(entity, token);
            return request;
        }

        public HashMap<String, String> generateHashmapFromEntity(CertificateEntity request, String accessToken) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", (request.getName()));
            map.put("farmer_id", (request.getFarmerId()));
            map.put("note", (request.getNote()));
            map.put("images", (request.getImages()));
            map.put("certificate_type_id", "1f6bcea0-0558-11e8-8174-1df78bb4c698");
            return map;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private final CertificateEntity entity;

        public ResponseValue(CertificateEntity entity) {
            this.entity = entity;
        }

        public CertificateEntity getCertificateEntities() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}

