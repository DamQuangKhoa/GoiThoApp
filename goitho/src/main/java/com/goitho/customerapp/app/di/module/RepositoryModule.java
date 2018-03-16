package com.goitho.customerapp.app.di.module;

import com.demo.architect.data.repository.action.product.remote.ActionProductRepository;
import com.demo.architect.data.repository.action.product.remote.ActionProductRepositoryImple;
import com.demo.architect.data.repository.activity.remote.ActivityRepository;
import com.demo.architect.data.repository.activity.remote.ActivityRepositoryImpl;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.data.repository.base.remote.RemoteRepository;
import com.demo.architect.data.repository.base.remote.RemoteRepositoryImpl;
import com.demo.architect.data.repository.base.local.DatabaseRealm;
import com.demo.architect.data.repository.base.local.LocalRepositoryImpl;
import com.demo.architect.data.repository.bill.remote.BillRepository;
import com.demo.architect.data.repository.bill.remote.BillRepositoryImpl;
import com.demo.architect.data.repository.buy.fetilizer.activity.remote.ActivityBuyFertilizerRepository;
import com.demo.architect.data.repository.buy.fetilizer.activity.remote.ActivityBuyFertilizerRepositoryImpl;
import com.demo.architect.data.repository.certificate.remote.CertificateRepository;
import com.demo.architect.data.repository.certificate.remote.CertificateRepositoryImpl;
import com.demo.architect.data.repository.employee.remote.EmployeeRepository;
import com.demo.architect.data.repository.employee.remote.EmployeeRepositoryImpl;
import com.demo.architect.data.repository.farmer.remote.FarmerRepository;
import com.demo.architect.data.repository.farmer.remote.FarmerRepositoryImpl;
import com.demo.architect.data.repository.login.remote.LoginRepository;
import com.demo.architect.data.repository.login.remote.LoginRepositoryImpl;
import com.demo.architect.data.repository.product.remote.ProductRepository;
import com.demo.architect.data.repository.product.remote.ProductRepositoryImpl;
import com.demo.architect.data.repository.upload.remote.UploadRepository;
import com.demo.architect.data.repository.upload.remote.UploadRepositoryImpl;
import com.demo.architect.data.repository.use.fertilizer.activity.remote.ActivityUseFertilizerRepository;
import com.demo.architect.data.repository.use.fertilizer.activity.remote.ActivityUseFertilizerRepositoryImpl;

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
    BillRepository provideBillRepository(BillRepositoryImpl billRepository) {
        return billRepository;
    }
    @Provides
    @Singleton
    LoginRepository provideLoginRepository(LoginRepositoryImpl apiServiceImp) {
        return apiServiceImp;
    }

    @Provides
    @Singleton
    EmployeeRepository provideEmployeeRepository(EmployeeRepositoryImpl apiServiceImp) {
        return apiServiceImp;
    }

    @Provides
    @Singleton
    FarmerRepository provideFarmerRepository(FarmerRepositoryImpl apiServiceImp) {
        return apiServiceImp;
    }

    @Provides
    @Singleton
    ActivityRepository provideActivityRepository(ActivityRepositoryImpl apiServiceImp) {
        return apiServiceImp;
    }

    @Provides
    @Singleton
    ProductRepository provideProductRepository(ProductRepositoryImpl apiServiceImp) {
        return apiServiceImp;
    }

    @Provides
    @Singleton
    ActionProductRepository provideActionProductRepository(ActionProductRepositoryImple apiServiceImp) {
        return apiServiceImp;
    }

    @Provides
    @Singleton
    UploadRepository provideUploadRepository(UploadRepositoryImpl apiServiceImp) {
        return apiServiceImp;
    }

    @Provides
    @Singleton
    CertificateRepository provideCertificateRepository(CertificateRepositoryImpl apiServiceImp) {
        return apiServiceImp;
    }

    @Provides
    @Singleton
    ActivityBuyFertilizerRepository provideActivityBuyFertilizerRepository(ActivityBuyFertilizerRepositoryImpl apiServiceImp) {
        return apiServiceImp;
    }

    @Provides
    @Singleton
    ActivityUseFertilizerRepository provideActivityUseFertilizerRepository(ActivityUseFertilizerRepositoryImpl apiServiceImp) {
        return apiServiceImp;
    }
}
