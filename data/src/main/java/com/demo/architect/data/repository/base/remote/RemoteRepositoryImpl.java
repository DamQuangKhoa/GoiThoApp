package com.demo.architect.data.repository.base.remote;

import com.demo.architect.data.model.EmployeeEntity;
import com.demo.architect.data.model.FarmerEntity;
import com.demo.architect.data.model.IMDBEntity;
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

    private void handleIMDBEntityResponse(Call<IMDBEntity> call, Subscriber subscriber) {
        try {
            IMDBEntity response = call.execute().body();
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

    @Override
    public Observable<IMDBEntity> getIMDB() {
        return Observable.create(new Observable.OnSubscribe<IMDBEntity>() {
            @Override
            public void call(Subscriber<? super IMDBEntity> subscriber) {
                handleIMDBEntityResponse(mRemoteApiInterface.getIMDB(), subscriber);
            }
        });
    }

    @Override
    public Observable<String> add(MessageModel model) {
        return null;
    }

    @Override
    public Observable<List<MessageModel>> findAll() {
        return null;
    }

    //LOGIN FARMER
    @Override
    public Observable<FarmerEntity> loginFarmer(final String email, final String password) {
        return Observable.create(new Observable.OnSubscribe<FarmerEntity>() {
            @Override
            public void call(Subscriber<? super FarmerEntity> subscriber) {
                handleLoginFarmerEntityResponse(mRemoteApiInterface.loginFarmer(email, password), subscriber);
            }
        });
    }

    private void handleLoginFarmerEntityResponse(Call<FarmerEntity> call, Subscriber subscriber) {
        try {
            FarmerEntity response = call.execute().body();
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

    //LOGIN EMPLOYEE
    @Override
    public Observable<EmployeeEntity> loginEmployee(final String email, final String password) {
        return Observable.create(new Observable.OnSubscribe<EmployeeEntity>() {
            @Override
            public void call(Subscriber<? super EmployeeEntity> subscriber) {
                handleLoginEmployeeEntityResponse(mRemoteApiInterface.loginEmployee(email, password), subscriber);
            }
        });
    }

    private void handleLoginEmployeeEntityResponse(Call<EmployeeEntity> call, Subscriber subscriber) {
        try {
            EmployeeEntity response = call.execute().body();
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


