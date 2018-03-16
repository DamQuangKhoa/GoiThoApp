package com.demo.architect.data.repository.upload.remote;

import android.net.Uri;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.UploadEntity;

import java.io.File;

import rx.Observable;

/**
 * Created by Skull on 13/01/2018.
 */

public interface UploadRepository {
    Observable<UploadEntity> uploadImage(File file);
}
