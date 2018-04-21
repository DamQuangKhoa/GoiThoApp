package com.goitho.customerapp.app.di.module;

import android.app.Application;

import com.demo.architect.data.repository.auth.remote.AuthApiInterface;
import com.demo.architect.data.repository.auth.remote.AuthRepositoryImpl;
import com.demo.architect.data.repository.base.remote.RemoteApiInterface;
import com.demo.architect.data.repository.base.remote.RemoteRepositoryImpl;
import com.demo.architect.data.repository.notification.remote.NotificationApiInterface;
import com.demo.architect.data.repository.notification.remote.NotificationRepositoryImpl;
import com.demo.architect.data.repository.order.remote.OrderApiInterface;
import com.demo.architect.data.repository.order.remote.OrderRepositoryImpl;
import com.demo.architect.data.repository.profile.remote.ProfileApiInterface;
import com.demo.architect.data.repository.profile.remote.ProfileRepositoryImpl;
import com.demo.architect.data.repository.upload.remote.UploadApiInterface;
import com.demo.architect.data.repository.upload.remote.UploadRepositoryImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by uyminhduc on 12/16/16.
 */
@Module
public class NetModule {
    private String mBaseUrl;

    public NetModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    RxJavaCallAdapterFactory provideRxJavaCallAdapter() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    @Singleton
    RemoteRepositoryImpl provideRetrofit(OkHttpClient okHttpClient, Gson gson, RxJavaCallAdapterFactory rxAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return new RemoteRepositoryImpl(retrofit.create(RemoteApiInterface.class));
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttp() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(35, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

    }
    @Provides
    @Singleton
    AuthRepositoryImpl provideAuthRetrofit(OkHttpClient okHttpClient, Gson gson, RxJavaCallAdapterFactory rxAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return new AuthRepositoryImpl(retrofit.create(AuthApiInterface.class));
    }

    @Provides
    @Singleton
    ProfileRepositoryImpl provideProfileRetrofit(OkHttpClient okHttpClient, Gson gson, RxJavaCallAdapterFactory rxAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return new ProfileRepositoryImpl(retrofit.create(ProfileApiInterface.class));
    }

    @Provides
    @Singleton
    UploadRepositoryImpl provideUploadRetrofit(OkHttpClient okHttpClient, Gson gson, RxJavaCallAdapterFactory rxAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return new UploadRepositoryImpl(retrofit.create(UploadApiInterface.class));
    }

    @Provides
    @Singleton
    OrderRepositoryImpl provideOrderRetrofit(OkHttpClient okHttpClient, Gson gson, RxJavaCallAdapterFactory rxAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return new OrderRepositoryImpl(retrofit.create(OrderApiInterface.class));
    }

    @Provides
    @Singleton
    NotificationRepositoryImpl provideNotificationRetrofit(OkHttpClient okHttpClient, Gson gson, RxJavaCallAdapterFactory rxAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return new NotificationRepositoryImpl(retrofit.create(NotificationApiInterface.class));
    }
}

