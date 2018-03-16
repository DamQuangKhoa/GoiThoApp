package com.goitho.customerapp.screen.certificate;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.model.CertificateEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.GetListCertificateByFarmerIdUsecase;
import com.demo.architect.domain.usecase.PostCertificateUsecase;
import com.demo.architect.domain.usecase.PostImageUsecase;
import com.demo.architect.domain.usecase.PutCertificateUsecase;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.manager.UserManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Skull on 30/11/2017.
 */

public class CertificatePresenter implements CertificateContract.Presenter{

    private final String TAG = CertificatePresenter.class.getName();
    private final CertificateContract.View view;

    private GetListCertificateByFarmerIdUsecase getListCertificateByFarmerIdUsecase;
    private PostCertificateUsecase postCertificateUsecase;
    private PutCertificateUsecase putCertificateUsecase;
    private PostImageUsecase postImageUsecase;
    boolean wasLoaded = false;

    @Inject
    LocalRepository localRepository;

    @Inject
    CertificatePresenter(@NonNull CertificateContract.View view,
                         GetListCertificateByFarmerIdUsecase getListCertificateByFarmerIdUsecase,
                         PostCertificateUsecase postCertificateUsecase,
                         PutCertificateUsecase putCertificateUsecase,
                         PostImageUsecase postImageUsecase) {
        this.view = view;
        this.getListCertificateByFarmerIdUsecase = getListCertificateByFarmerIdUsecase;
        this.postCertificateUsecase = postCertificateUsecase;
        this.putCertificateUsecase = putCertificateUsecase;
        this.postImageUsecase = postImageUsecase;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        if (!wasLoaded) {
            loadCertiicates();
            wasLoaded = true;
        }
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }

    @Override
    public void loadCertiicates() {
        view.showProgressBar();
        getListCertificateByFarmerIdUsecase.executeIO(new GetListCertificateByFarmerIdUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).getString(Constants.KEY_TOKEN,""),
                        UserManager.getInstance().getFarmerUser().getId()),
                new BaseUseCase.UseCaseCallback<GetListCertificateByFarmerIdUsecase.ResponseValue, GetListCertificateByFarmerIdUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(GetListCertificateByFarmerIdUsecase.ResponseValue successResponse) {
                        List<CertificateEntity> list = successResponse.getListCertificate();
                        view.showCertificates(list);
                        view.hideProgressBar();
                    }

                    @Override
                    public void onError(GetListCertificateByFarmerIdUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }

    @Override
    public void createCertificate(CertificateEntity entity) {
        view.showProgressBar();
        postCertificateUsecase.executeIO(new PostCertificateUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .getString(Constants.KEY_TOKEN,""),
                        entity
                ),
                new BaseUseCase.UseCaseCallback<PostCertificateUsecase.ResponseValue, PostCertificateUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(PostCertificateUsecase.ResponseValue successResponse) {
                        view.hideProgressBar();
                        view.finishActivity();
                    }

                    @Override
                    public void onError(PostCertificateUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }

    @Override
    public void editCertificate(CertificateEntity entity) {
        view.showProgressBar();
        putCertificateUsecase.executeIO(new PutCertificateUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .getString(Constants.KEY_TOKEN,""), entity.getId(),
                        entity
                ),
                new BaseUseCase.UseCaseCallback<PutCertificateUsecase.ResponseValue, PutCertificateUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(PutCertificateUsecase.ResponseValue successResponse) {
                        view.hideProgressBar();
                        view.finishActivity();
                    }

                    @Override
                    public void onError(PutCertificateUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }

    @Override
    public void uploadImage(CertificateEntity entity, ArrayList<File> listFile, boolean isCreating) {
        view.showProgressBar();
        CertificateEntity fakeEntity = entity;
        int imageNumber =0;
        if (!TextUtils.isEmpty(entity.getImages()))
            imageNumber = entity.getImages().split(",").length;
        int tmp = imageNumber;
        for(int i=0; i < listFile.size(); i++) {
            postImageUsecase.executeIO(new PostImageUsecase.RequestValue(listFile.get(i)),
                    new BaseUseCase.UseCaseCallback<PostImageUsecase.ResponseValue, PostImageUsecase.ErrorValue>() {
                        @Override
                        public void onSuccess(PostImageUsecase.ResponseValue successResponse) {
                            if(!TextUtils.isEmpty(fakeEntity.getImages())) {
                                fakeEntity.setImages(fakeEntity.getImages() + "," +
                                        successResponse.getUploadEntitie().getImageUrl());
                            } else {
                                fakeEntity.setImages(successResponse.getUploadEntitie().getImageUrl());
                            }
                            String[] string = fakeEntity.getImages().split(",");
                            if(string.length - tmp == listFile.size()) {
                                if(isCreating)
                                    createCertificate(fakeEntity);
                                else
                                    editCertificate(fakeEntity);
                            }
                        }

                        @Override
                        public void onError(PostImageUsecase.ErrorValue errorResponse) {
                            view.hideProgressBar();
                            view.showError();
                        }
                    });
        }
    }
}
