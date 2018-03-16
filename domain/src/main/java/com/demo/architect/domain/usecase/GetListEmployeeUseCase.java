package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.EmployeeEntity;
import com.demo.architect.data.repository.employee.remote.EmployeeRepository;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 05/01/2018.
 */

public class GetListEmployeeUseCase extends BaseUseCase {
    private static final String TAG = GetListEmployeeUseCase.class.getSimpleName();
    private final EmployeeRepository remoteRepository;

    public GetListEmployeeUseCase(EmployeeRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        String token = ((RequestValue) requestValues).token;
        return remoteRepository.findListEmployee(token);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<List<EmployeeEntity>>>() {
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
            public void onNext(BaseResponse<List<EmployeeEntity>> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    List<EmployeeEntity> result = data.getResultObject();
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

        public RequestValue(String token) {
            this.token = token;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private final List<EmployeeEntity> listEmployee;

        public ResponseValue(List<EmployeeEntity> listEmployee) {
            this.listEmployee = listEmployee;
        }

        public List<EmployeeEntity> getListEmployee() {
            return listEmployee;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
