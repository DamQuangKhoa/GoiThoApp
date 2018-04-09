package com.demo.architect.domain.usecase;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.UpdateUserEntity;
import com.demo.architect.data.model.UserEntity;
import com.demo.architect.data.repository.auth.remote.AuthRepository;
import com.demo.architect.data.repository.profile.remote.ProfileRepository;

import rx.Observable;
import rx.Subscriber;


public class UpdateUserProfileUsecase extends BaseUseCase {
    private static final String TAG = UpdateUserProfileUsecase.class.getSimpleName();
    private final ProfileRepository remoteRepository;

    public UpdateUserProfileUsecase(ProfileRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        UserEntity userEntity = ((RequestValue) requestValues).userEntity;
        return remoteRepository.updateUserProfile(userEntity);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<BaseResponse<UpdateUserEntity>>() {
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
            public void onNext(BaseResponse<UpdateUserEntity> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data.getCode()));
                if (useCaseCallback != null) {
                    UpdateUserEntity result = data.getResponse();
                    if (result != null) {
                        UserEntity entity = new UserEntity(result.getUserInfo());
                        useCaseCallback.onSuccess(new ResponseValue(entity));
                    } else {
                        useCaseCallback.onError(new ErrorValue());
                    }
                }
            }
        };
    }

    public static final class RequestValue implements RequestValues {
        public final UserEntity userEntity;

        public RequestValue(UserEntity userEntity) {
            this.userEntity = userEntity;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private UserEntity entity;

        public ResponseValue(UserEntity entity) {
            this.entity = entity;
        }

        public UserEntity getEntity() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}
