package com.goitho.customerapp.app.di.module;

import android.app.Application;

import com.demo.architect.data.repository.action.product.remote.ActionProductApiInterface;
import com.demo.architect.data.repository.action.product.remote.ActionProductRepositoryImple;
import com.demo.architect.data.repository.activity.remote.ActivityApiInterface;
import com.demo.architect.data.repository.activity.remote.ActivityRepositoryImpl;
import com.demo.architect.data.repository.base.remote.RemoteRepositoryImpl;
import com.demo.architect.data.repository.base.remote.RemoteApiInterface;
import com.demo.architect.data.repository.bill.remote.BillApiInterface;
import com.demo.architect.data.repository.bill.remote.BillRepositoryImpl;
import com.demo.architect.data.repository.buy.fetilizer.activity.remote.ActivityBuyFertilizerApiInterface;
import com.demo.architect.data.repository.buy.fetilizer.activity.remote.ActivityBuyFertilizerRepositoryImpl;
import com.demo.architect.data.repository.certificate.remote.CertificateApiInterface;
import com.demo.architect.data.repository.certificate.remote.CertificateRepositoryImpl;
import com.demo.architect.data.repository.employee.remote.EmployeeApiInterface;
import com.demo.architect.data.repository.employee.remote.EmployeeRepositoryImpl;
import com.demo.architect.data.repository.farmer.remote.FarmerApiInterface;
import com.demo.architect.data.repository.farmer.remote.FarmerRepositoryImpl;
import com.demo.architect.data.repository.login.remote.LoginApiInterface;
import com.demo.architect.data.repository.login.remote.LoginRepositoryImpl;
import com.demo.architect.data.repository.product.remote.ProductApiInterface;
import com.demo.architect.data.repository.product.remote.ProductRepositoryImpl;
import com.demo.architect.data.repository.upload.remote.UploadApiInterface;
import com.demo.architect.data.repository.upload.remote.UploadRepositoryImpl;
import com.demo.architect.data.repository.use.fertilizer.activity.remote.ActivityUseFertilizerApiInterface;
import com.demo.architect.data.repository.use.fertilizer.activity.remote.ActivityUseFertilizerRepositoryImpl;
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
    BillRepositoryImpl provideBillRetrofit(OkHttpClient okHttpClient, Gson gson, RxJavaCallAdapterFactory rxAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return new BillRepositoryImpl(retrofit.create(RemoteApiInterface.class),retrofit.create(BillApiInterface.class));
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
    LoginRepositoryImpl provideLoginRetrofit(OkHttpClient okHttpClient, Gson gson, RxJavaCallAdapterFactory rxAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return new LoginRepositoryImpl(retrofit.create(LoginApiInterface.class));
    }

    @Provides
    @Singleton
    FarmerRepositoryImpl provideFarmerRetrofit(OkHttpClient okHttpClient, Gson gson, RxJavaCallAdapterFactory rxAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return new FarmerRepositoryImpl(retrofit.create(FarmerApiInterface.class));
    }

    @Provides
    @Singleton
    EmployeeRepositoryImpl provideEmployeeRetrofit(OkHttpClient okHttpClient, Gson gson, RxJavaCallAdapterFactory rxAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return new EmployeeRepositoryImpl(retrofit.create(EmployeeApiInterface.class));
    }

    @Provides
    @Singleton
    ActivityRepositoryImpl provideActivityRetrofit(OkHttpClient okHttpClient, Gson gson, RxJavaCallAdapterFactory rxAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return new ActivityRepositoryImpl(retrofit.create(ActivityApiInterface.class));
    }

    @Provides
    @Singleton
    ProductRepositoryImpl provideProductRetrofit(OkHttpClient okHttpClient, Gson gson, RxJavaCallAdapterFactory rxAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return new ProductRepositoryImpl(retrofit.create(ProductApiInterface.class));
    }

    @Provides
    @Singleton
    ActionProductRepositoryImple provideActionProductRetrofit(OkHttpClient okHttpClient, Gson gson, RxJavaCallAdapterFactory rxAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return new ActionProductRepositoryImple(retrofit.create(ActionProductApiInterface.class));
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
    CertificateRepositoryImpl provideCertificateRetrofit(OkHttpClient okHttpClient, Gson gson, RxJavaCallAdapterFactory rxAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return new CertificateRepositoryImpl(retrofit.create(CertificateApiInterface.class));
    }

    @Provides
    @Singleton
    ActivityBuyFertilizerRepositoryImpl provideActivityBuyFertilizerRetrofit(OkHttpClient okHttpClient, Gson gson, RxJavaCallAdapterFactory rxAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return new ActivityBuyFertilizerRepositoryImpl(retrofit.create(ActivityBuyFertilizerApiInterface.class));
    }

    @Provides
    @Singleton
    ActivityUseFertilizerRepositoryImpl provideActivityUseFertilizerRetrofit(OkHttpClient okHttpClient, Gson gson, RxJavaCallAdapterFactory rxAdapterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return new ActivityUseFertilizerRepositoryImpl(retrofit.create(ActivityUseFertilizerApiInterface.class));
    }
}

