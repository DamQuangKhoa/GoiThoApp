package com.demo.architect.domain.usecase;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.UploadEntity;
import com.demo.architect.data.repository.upload.remote.UploadRepository;

import java.io.File;
import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 13/01/2018.
 */

public class PostImageUsecase extends BaseUseCase {
    private static final String TAG = PostImageUsecase.class.getSimpleName();
    private final UploadRepository remoteRepository;

    public PostImageUsecase(UploadRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return remoteRepository.uploadImage(
                ((RequestValue) requestValues).file);
    }

    @Override
    protected Subscriber buildUseCaseSubscriber() {
        return new Subscriber<UploadEntity>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
                if (useCaseCallback != null) {
                    useCaseCallback.onError(new PostImageUsecase.ErrorValue());
                }
            }

            @Override
            public void onNext(UploadEntity data) {
                if (useCaseCallback != null) {
                    if (data != null ) {
                        useCaseCallback.onSuccess(new PostImageUsecase.ResponseValue(data));
                    } else {
                        useCaseCallback.onError(new PostImageUsecase.ErrorValue());
                    }
                }
            }
        };
    }

    public static final class RequestValue implements RequestValues {
        private String token;
        private File file;

        public RequestValue( File file) {
            this.file = file;
        }

//        public HashMap<String, String> getRequestValue() {
//            HashMap<String, String> request = generateHashmapFromEntity(entity, token);
//            return request;
//        }
//
//        public HashMap<String, String> generateHashmapFromEntity(ActivityEntity request, String accessToken) {
//            HashMap<String, String> map = new HashMap<>();
//            map.put("farmer_id", (request.getFarmerId()));
//            map.put("product_id", (request.getProductId()));
//            map.put("product_action_id", (request.getProductActionId()));
//            map.put("activity_action_id", (request.getActivityActionId()));
//            map.put("assignee_id", (request.getAssigneeId()));
//            map.put("note", request.getNote());
//            map.put("images", (request.getImages()));
//            return map;
//        }

        public File getFile() {
            return file;
        }

//        public File generateFileFromUri(Uri request){
//            File file = new  File(request.getPath());
//            return file;
//        }
//
//        public File getRequestValue() {
//            File request = generateFileFromUri(uri);
//            return request;
//        }
    }

    public static final class ResponseValue implements ResponseValues {
        private final UploadEntity entity;

        public ResponseValue(UploadEntity entity) {
            this.entity = entity;
        }

        public UploadEntity getUploadEntitie() {
            return entity;
        }
    }

    public static final class ErrorValue implements ErrorValues {
    }
}

