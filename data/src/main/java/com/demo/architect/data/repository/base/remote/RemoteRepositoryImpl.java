package com.demo.architect.data.repository.base.remote;

import com.demo.architect.data.model.MessageModel;

//import javax.inject.Singleton;

import java.util.List;

import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by uyminhduc on 10/16/16.
 */
//@Singleton
public class RemoteRepositoryImpl implements RemoteRepository {

    private final static String TAG = RemoteRepositoryImpl.class.getName();

    private RemoteApiInterface mRemoteApiInterface;

    public RemoteRepositoryImpl(RemoteApiInterface mRemoteApiInterface) {
        this.mRemoteApiInterface = mRemoteApiInterface;
    }
}


