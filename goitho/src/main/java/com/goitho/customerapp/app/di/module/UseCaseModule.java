package com.goitho.customerapp.app.di.module;

import com.demo.architect.data.repository.action.product.remote.ActionProductRepository;
import com.demo.architect.data.repository.activity.remote.ActivityRepository;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.data.repository.bill.remote.BillRepository;
import com.demo.architect.data.repository.buy.fetilizer.activity.remote.ActivityBuyFertilizerRepository;
import com.demo.architect.data.repository.certificate.remote.CertificateRepository;
import com.demo.architect.data.repository.employee.remote.EmployeeRepository;
import com.demo.architect.data.repository.farmer.remote.FarmerRepository;
import com.demo.architect.data.repository.login.remote.LoginRepository;
import com.demo.architect.data.repository.product.remote.ProductRepository;
import com.demo.architect.data.repository.upload.remote.UploadRepository;
import com.demo.architect.data.repository.use.fertilizer.activity.remote.ActivityUseFertilizerRepository;
import com.demo.architect.domain.usecase.DeleteActionProductUsecase;
import com.demo.architect.domain.usecase.DeleteActivityUsecase;
import com.demo.architect.domain.usecase.DeleteBuyFertilizerUsecase;
import com.demo.architect.domain.usecase.DeleteUseFertilizerUseCase;
import com.demo.architect.domain.usecase.GetHistoryActivityUsecase;
import com.demo.architect.domain.usecase.GetListActionProductUsecase;
import com.demo.architect.domain.usecase.GetListActivityUsecase;
import com.demo.architect.domain.usecase.GetListCertificateByFarmerIdUsecase;
import com.demo.architect.domain.usecase.GetListEmployeeUseCase;
import com.demo.architect.domain.usecase.GetListFarmerByEmployeeIdUsecase;
import com.demo.architect.domain.usecase.GetListFarmerUsecase;
import com.demo.architect.domain.usecase.GetProductUsecase;
import com.demo.architect.domain.usecase.LoginEmployeeUsecase;
import com.demo.architect.domain.usecase.LoginFarmerUsecase;
import com.demo.architect.domain.usecase.PostActionProductUsecase;
import com.demo.architect.domain.usecase.PostActivityBuyFertilizerUsecase;
import com.demo.architect.domain.usecase.PostActivityUseFertilizerUsecase;
import com.demo.architect.domain.usecase.PostActivityUsecase;
import com.demo.architect.domain.usecase.PostCertificateUsecase;
import com.demo.architect.domain.usecase.PostImageUsecase;
import com.demo.architect.domain.usecase.PutActivityBuyFertilizerUsecase;
import com.demo.architect.domain.usecase.PutActivityUseFertilizerUsecase;
import com.demo.architect.domain.usecase.PutActivityUsecase;
import com.demo.architect.domain.usecase.PutCertificateUsecase;
import com.demo.architect.domain.usecase.PutEmployeeUsecase;
import com.demo.architect.domain.usecase.PutFarmerUsecase;
import com.demo.architect.domain.usecase.demo.IMDBUsecase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by uyminhduc on 12/16/16.
 */
@Module
public class UseCaseModule {
    public UseCaseModule() {
    }

    @Provides
    IMDBUsecase provideIMDBUsecase(BillRepository mApiRepository, LocalRepository localRepository) {
        return new IMDBUsecase(mApiRepository, localRepository);
    }

    @Provides
    LoginFarmerUsecase provideLoginFarmerUsecase(LoginRepository remoteRepository) {
        return new LoginFarmerUsecase(remoteRepository);
    }

    @Provides
    LoginEmployeeUsecase provideLoginEmployeeUsecase(LoginRepository remoteRepository) {
        return new LoginEmployeeUsecase(remoteRepository);
    }

    @Provides
    GetListFarmerUsecase provideGetListFarmerUseCase(FarmerRepository remoteRepository) {
        return new GetListFarmerUsecase(remoteRepository);
    }

    @Provides
    GetListEmployeeUseCase provideGetListEmployeeUseCase(EmployeeRepository remoteRepository) {
        return new GetListEmployeeUseCase(remoteRepository);
    }

    @Provides
    GetListActivityUsecase provideGetListActivityUsecase(ActivityRepository remoteRepository) {
        return new GetListActivityUsecase(remoteRepository);
    }

    @Provides
    GetHistoryActivityUsecase provideGetHistoryActivityUsecase(ActivityRepository remoteRepository) {
        return new GetHistoryActivityUsecase(remoteRepository);
    }

    @Provides
    GetListFarmerByEmployeeIdUsecase provideGetListFarmerByEmployeeIdUseCase(FarmerRepository remoteRepository) {
        return new GetListFarmerByEmployeeIdUsecase(remoteRepository);
    }

    @Provides
    GetProductUsecase provideGetProductUsecase(ProductRepository remoteRepository) {
        return new GetProductUsecase(remoteRepository);
    }

    @Provides
    GetListActionProductUsecase provideGetListActionProductUsecase(ActionProductRepository remoteRepository) {
        return new GetListActionProductUsecase(remoteRepository);
    }

    @Provides
    DeleteActionProductUsecase provideDeleteActionProductUsecase(ActionProductRepository remoteRepository) {
        return new DeleteActionProductUsecase(remoteRepository);
    }

    @Provides
    PostActionProductUsecase providePostActionProductUsecase(ActionProductRepository remoteRepository) {
        return new PostActionProductUsecase(remoteRepository);
    }

    @Provides
    PostActivityUsecase providePostActivityUsecase(ActivityRepository remoteRepository) {
        return new PostActivityUsecase(remoteRepository);
    }

    @Provides
    PutActivityUsecase providePutActivityUsecase(ActivityRepository remoteRepository) {
        return new PutActivityUsecase(remoteRepository);
    }

    @Provides
    DeleteActivityUsecase provideDeleteActivityUsecase(ActivityRepository remoteRepository) {
        return new DeleteActivityUsecase(remoteRepository);
    }

    @Provides
    PostImageUsecase providePostImageUsecase(UploadRepository remoteRepository) {
        return new PostImageUsecase(remoteRepository);
    }

    @Provides
    GetListCertificateByFarmerIdUsecase provideGetListCertificateByFarmerId(CertificateRepository remoteRepository) {
        return new GetListCertificateByFarmerIdUsecase(remoteRepository);
    }

    @Provides
    PostActivityBuyFertilizerUsecase providePostActivityBuyFertilizerUsecase(ActivityBuyFertilizerRepository remoteRepository) {
        return new PostActivityBuyFertilizerUsecase(remoteRepository);
    }

    @Provides
    PutActivityBuyFertilizerUsecase providePutActivityBuyFertilizerUsecase(ActivityBuyFertilizerRepository remoteRepository) {
        return new PutActivityBuyFertilizerUsecase(remoteRepository);
    }

    @Provides
    PostActivityUseFertilizerUsecase providePostActivityUseFertilizerUsecase(ActivityUseFertilizerRepository remoteRepository) {
        return new PostActivityUseFertilizerUsecase(remoteRepository);
    }

    @Provides
    PutActivityUseFertilizerUsecase providePutActivityUseFertilizerUsecase(ActivityUseFertilizerRepository remoteRepository) {
        return new PutActivityUseFertilizerUsecase(remoteRepository);
    }

    @Provides
    DeleteBuyFertilizerUsecase provideDeleteBuyFertilizerUsecase(ActivityBuyFertilizerRepository remoteRepository) {
        return new DeleteBuyFertilizerUsecase(remoteRepository);
    }

    @Provides
    DeleteUseFertilizerUseCase provideDeleteUseFertilizerUseCase(ActivityUseFertilizerRepository remoteRepository) {
        return new DeleteUseFertilizerUseCase(remoteRepository);
    }

    @Provides
    PutEmployeeUsecase providePutEmployeeUsecase(EmployeeRepository remoteRepository) {
        return new PutEmployeeUsecase(remoteRepository);
    }

    @Provides
    PutFarmerUsecase providePutFarmerUsecase(FarmerRepository remoteRepository) {
        return new PutFarmerUsecase(remoteRepository);
    }

    @Provides
    PutCertificateUsecase providePutCertificateUsecase(CertificateRepository remoteRepository) {
        return new PutCertificateUsecase(remoteRepository);
    }

    @Provides
    PostCertificateUsecase providePostCertificateUsecase(CertificateRepository remoteRepository) {
        return new PostCertificateUsecase(remoteRepository);
    }
}

