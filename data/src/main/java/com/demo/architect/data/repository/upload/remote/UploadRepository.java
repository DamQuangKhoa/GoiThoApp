package com.demo.architect.data.repository.upload.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.UploadEntity;

import rx.Observable;

/**
 * Created by Skull on 13/01/2018.
 */

public interface UploadRepository {
    Observable<BaseResponse<UploadEntity>> uploadImageSetCalendar(String userId, String orderId, String image);
}
