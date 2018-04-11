package com.goitho.customerapp.app.di.module;

import com.demo.architect.data.repository.auth.remote.AuthRepository;
import com.demo.architect.data.repository.auth.remote.AuthRepositoryImpl;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.data.repository.base.remote.RemoteRepository;
import com.demo.architect.data.repository.base.remote.RemoteRepositoryImpl;
import com.demo.architect.data.repository.base.local.DatabaseRealm;
import com.demo.architect.data.repository.base.local.LocalRepositoryImpl;
import com.demo.architect.data.repository.order.remote.OrderRepository;
import com.demo.architect.data.repository.order.remote.OrderRepositoryImpl;
import com.demo.architect.data.repository.profile.remote.ProfileRepository;
import com.demo.architect.data.repository.profile.remote.ProfileRepositoryImpl;
import com.demo.architect.data.repository.upload.remote.UploadRepository;
import com.demo.architect.data.repository.upload.remote.UploadRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public LocalRepository provideMessageRepository() {
        return new LocalRepositoryImpl();
    }

    @Provides
    @Singleton
    public DatabaseRealm provideDatabaseRealm() {
        return new DatabaseRealm();
    }

    @Provides
    @Singleton
    RemoteRepository provideUserRepository(RemoteRepositoryImpl apiServiceImp) {
        return apiServiceImp;
    }

    @Provides
    @Singleton
    AuthRepository provideAuthRepository(AuthRepositoryImpl apiServiceImp) {
        return apiServiceImp;
    }

    @Provides
    @Singleton
    ProfileRepository provideProfileRepository(ProfileRepositoryImpl apiServiceImp) {
        return apiServiceImp;
    }

    @Provides
    @Singleton
    UploadRepository provideUploadRepository(UploadRepositoryImpl apiServiceImp) {
        return apiServiceImp;
    }

    @Provides
    @Singleton
    OrderRepository provideOrderRepository(OrderRepositoryImpl apiServiceImp) {
        return apiServiceImp;
    }
}
