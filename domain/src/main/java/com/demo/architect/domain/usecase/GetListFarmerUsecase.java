package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.FarmerEntity;
import com.demo.architect.data.repository.farmer.remote.FarmerRepository;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 05/01/2018.
 */

public class GetListFarmerUsecase extends BaseUseCase {
    private static final String TAG = GetListFarmerUsecase.class.getSimpleName();
    private final FarmerRepository remoteRepository;

    public GetListFarmerUsecase(FarmerRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return remoteRepository.findListFarmer((((RequestValue)requestValues).token));
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<List<FarmerEntity>>>() {
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
            public void onNext(BaseResponse<List<FarmerEntity>> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    List<FarmerEntity> result = data.getResultObject();
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
        String token;
        public RequestValue(String token) {
            this.token = token;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private final List<FarmerEntity> listFarmer;

        public ResponseValue(List<FarmerEntity> listFarmer) {
            this.listFarmer = listFarmer;
        }

        public List<FarmerEntity> getListFarmer() {
            return listFarmer;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
