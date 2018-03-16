package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.EmployeeEntity;
import com.demo.architect.data.repository.base.remote.RemoteRepository;
import com.demo.architect.data.repository.login.remote.LoginRepository;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 19/12/2017.
 */

public class LoginEmployeeUsecase extends BaseUseCase {
    private static final String TAG = LoginEmployeeUsecase.class.getSimpleName();
    private final LoginRepository remoteRepository;

    public LoginEmployeeUsecase(LoginRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        String email = ((RequestValue) requestValues).email;
        String password = ((RequestValue) requestValues).password;
        return remoteRepository.loginEmployee(email, password);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<EmployeeEntity>>() {
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
            public void onNext(BaseResponse<EmployeeEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    EmployeeEntity result = data.getResultObject();
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
        public final String email;
        public final String password;

        public RequestValue(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private EmployeeEntity entity;

        public ResponseValue(EmployeeEntity entity) {
            this.entity = entity;
        }

        public EmployeeEntity getEntity() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
