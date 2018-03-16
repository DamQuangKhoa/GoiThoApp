package com.demo.architect.data.repository.upload.remote;

import com.demo.architect.data.model.UploadEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Skull on 13/01/2018.
 */

public class UploadRepositoryImpl implements UploadRepository {

    private final static String TAG = UploadRepositoryImpl.class.getName();

    private UploadApiInterface mRemoteApiInterface;

    public UploadRepositoryImpl(UploadApiInterface mRemoteApiInterface) {
        this.mRemoteApiInterface = mRemoteApiInterface;
    }

    // Returns the contents of the file in a byte array.
    private byte[] getBytesFromFile(File file) throws IOException {
        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
            throw new IOException("File is too large!");
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;

        InputStream is = new FileInputStream(file);
        try {
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
        } finally {
            is.close();
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        return bytes;
    }

    @Override
    public Observable<UploadEntity> uploadImage(final File file) {
        return Observable.create(new Observable.OnSubscribe<UploadEntity>() {
            @Override
            public void call(Subscriber<? super UploadEntity> subscriber) {
                RequestBody reqFile = null;
                try {
                    reqFile = RequestBody.create(MediaType.parse("image/*"), getBytesFromFile(file));
                } catch (IOException e) {
                    reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                    e.printStackTrace();
                }

                handleUploadImageResponse(mRemoteApiInterface.uploadImage(reqFile), subscriber);
            }
        });
    }

    private void handleUploadImageResponse(Call<UploadEntity> call, Subscriber subscriber) {
        try {
            UploadEntity response = call.clone().execute().body();

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
