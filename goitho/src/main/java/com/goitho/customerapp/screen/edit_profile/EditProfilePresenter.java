package com.goitho.customerapp.screen.edit_profile;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.model.EmployeeEntity;
import com.demo.architect.data.model.FarmerEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.PostImageUsecase;
import com.demo.architect.domain.usecase.PutFarmerUsecase;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.manager.UserManager;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by Skull on 29/11/2017.
 */

public class EditProfilePresenter implements EditProfileContract.Presenter {

    private final String TAG = EditProfilePresenter.class.getName();
    private final EditProfileContract.View view;
    private boolean isEmployee;

    private PostImageUsecase postImageUsecase;
    private PutFarmerUsecase putFarmerUsecase;

    @Inject
    LocalRepository localRepository;

    @Inject
    EditProfilePresenter(@NonNull EditProfileContract.View view, PostImageUsecase postImageUsecase,
                         PutFarmerUsecase putFarmerUsecase) {
        this.view = view;
        this.postImageUsecase = postImageUsecase;
        this.putFarmerUsecase = putFarmerUsecase;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        loadDataOfNavigation();
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }

    @Override
    public void loadDataOfNavigation() {
        if (UserManager.getInstance().getEmployeeUser() != null) {
            EmployeeEntity user = UserManager.getInstance().getEmployeeUser();
            String code = user.getId();
            if (!TextUtils.isEmpty(user.getCode())) {
                switch (user.getCode().length()) {
                    case 1:
                        code = "NV00" + user.getCode();
                        break;
                    case 2:
                        code = "NV0" + user.getCode();
                        break;
                    default:
                        code = "NV" + user.getCode();
                        break;
                }
            }
            view.showDataToNavigation(user.getName(), "", null, null,
                    code, isEmployee);
        } else {
            FarmerEntity user = UserManager.getInstance().getFarmerUser();
            String code = user.getId();
            if (!TextUtils.isEmpty(user.getCode())) {
                switch (user.getCode().length()) {
                    case 1:
                        code = "NH00" + user.getCode();
                        break;
                    case 2:
                        code = "NH0" + user.getCode();
                        break;
                    default:
                        code = "NH" + user.getCode();
                        break;
                }
            }
            view.showDataToNavigation(user.getName(), user.getAddress(), user.getAvatar(), user.getRating(),
                    code, isEmployee);
        }
    }

    @Override
    public void loadMapData() {
        if (isEmployee) {
            view.showMapData(null, null);
        } else if (UserManager.getInstance().getFarmerUser() != null) {
            FarmerEntity farmer = UserManager.getInstance().getFarmerUser();
            view.showMapData(farmer.getLatitude(), farmer.getLongitude());
        }
    }

    @Override
    public void uploadImage(File file) {
        view.showProgressBar();
        postImageUsecase.executeIO(new PostImageUsecase.RequestValue(file),
                new BaseUseCase.UseCaseCallback<PostImageUsecase.ResponseValue, PostImageUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(PostImageUsecase.ResponseValue successResponse) {
                        UserManager.getInstance().getFarmerUser().setAvatar(successResponse.getUploadEntitie().getImageUrl());
                        putFarmer(UserManager.getInstance().getFarmerUser());
                    }

                    @Override
                    public void onError(PostImageUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }

    public void putFarmer(FarmerEntity farmerEntity) {
        view.showProgressBar();
        putFarmerUsecase.executeIO(new PutFarmerUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .getString(Constants.KEY_TOKEN, ""), farmerEntity.getId(),
                        farmerEntity
                ),
                new BaseUseCase.UseCaseCallback<PutFarmerUsecase.ResponseValue, PutFarmerUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(PutFarmerUsecase.ResponseValue successResponse) {
                        loadDataOfNavigation();
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .pushFarmerObject(farmerEntity);
                        view.hideProgressBar();
                    }

                    @Override
                    public void onError(PutFarmerUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }

    public void setIsEmployee(boolean isEmployee) {
        this.isEmployee = isEmployee;
    }
}
