package com.goitho.customerapp.screen.edit_address;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.model.UserEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.UpdateUserProfileUsecase;
import com.demo.architect.utils.view.ImageUtil;
import com.goitho.customerapp.manager.UserManager;

import javax.inject.Inject;

/**
 * Created by Skull on 29/11/2017.
 */

public class EditAddressPresenter implements EditAddressContract.Presenter {

    private final String TAG = EditAddressPresenter.class.getName();
    private final EditAddressContract.View view;
    private final UpdateUserProfileUsecase updateUserProfileUsecase;

    @Inject
    LocalRepository localRepository;

    @Inject
    EditAddressPresenter(@NonNull EditAddressContract.View view, UpdateUserProfileUsecase updateUserProfileUsecase) {
        this.view = view;
        this.updateUserProfileUsecase = updateUserProfileUsecase;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");

    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }

    @Override
    public void editProfile(Bitmap avatar, String fullName, String address1, String address2, String email) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(UserManager.getInstance().getUser().getUserId());
        userEntity.setAvatarImageUrl(ImageUtil.encodeImageBase64(avatar));
        userEntity.setUserFullName(fullName);
        userEntity.setAddress1(address1);
        userEntity.setAddress2(address2);
        userEntity.setEmail(email);
        view.showProgressBar();
        updateUserProfileUsecase.executeIO(new UpdateUserProfileUsecase.RequestValue(userEntity),
                new BaseUseCase.UseCaseCallback<UpdateUserProfileUsecase.ResponseValue,
                        UpdateUserProfileUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(UpdateUserProfileUsecase.ResponseValue successResponse) {
                        view.startRegisterSuccessActivity();
                    }

                    @Override
                    public void onError(UpdateUserProfileUsecase.ErrorValue errorResponse) {

                    }
                });
    }
}
