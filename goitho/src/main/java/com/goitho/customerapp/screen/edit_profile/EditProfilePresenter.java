package com.goitho.customerapp.screen.edit_profile;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.ResetPhoneNumberUsecase;
import com.goitho.customerapp.manager.UserManager;

import javax.inject.Inject;

/**
 * Created by Skull on 29/11/2017.
 */

public class EditProfilePresenter implements EditProfileContract.Presenter {

    private final String TAG = EditProfilePresenter.class.getName();
    private final EditProfileContract.View view;
    private final ResetPhoneNumberUsecase resetPhoneNumberUsecase;

    @Inject
    LocalRepository localRepository;

    @Inject
    EditProfilePresenter(@NonNull EditProfileContract.View view, ResetPhoneNumberUsecase resetPhoneNumberUsecase) {
        this.view = view;
        this.resetPhoneNumberUsecase = resetPhoneNumberUsecase;
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
    public void editAccount(String pass, String newPhoneNumber) {
        resetPhoneNumberUsecase.executeIO(new ResetPhoneNumberUsecase.RequestValue(UserManager.getInstance().getUser().getUserName(),
                        pass, newPhoneNumber),
                new BaseUseCase.UseCaseCallback
                        <ResetPhoneNumberUsecase.ResponseValue, ResetPhoneNumberUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(ResetPhoneNumberUsecase.ResponseValue successResponse) {
                        view.startPhoneVerificationActivity(UserManager.getInstance().getUser().getUserId(), newPhoneNumber);
                    }

                    @Override
                    public void onError(ResetPhoneNumberUsecase.ErrorValue errorResponse) {
                        view.showError();
                    }
                });
    }
}
