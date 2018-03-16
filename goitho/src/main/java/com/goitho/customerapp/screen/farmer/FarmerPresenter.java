package com.goitho.customerapp.screen.farmer;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.model.EmployeeEntity;
import com.demo.architect.data.model.FarmerEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.GetListFarmerByEmployeeIdUsecase;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.manager.UserManager;

import javax.inject.Inject;

/**
 * Created by Skull on 27/11/2017.
 */

public class FarmerPresenter implements FarmerContract.Presenter{

    private final String TAG = FarmerPresenter.class.getName();
    private final FarmerContract.View view;
    private GetListFarmerByEmployeeIdUsecase getListFarmerUseCase;

    @Inject
    LocalRepository localRepository;

    @Inject
    FarmerPresenter(@NonNull FarmerContract.View view, GetListFarmerByEmployeeIdUsecase getListFarmerUseCase) {
        this.view = view;
        this.getListFarmerUseCase = getListFarmerUseCase;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        loadListFarmer();
        loadDataOfNavigation();
        Log.d(TAG, TAG + ".start() called");
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }

    @Override
    public void loadListFarmer() {

        view.showProgressBar();
        getListFarmerUseCase.executeIO(new GetListFarmerByEmployeeIdUsecase.RequestValue(
                SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).getString(Constants.KEY_TOKEN,""),
                        UserManager.getInstance().getEmployeeUser().getId()),
                new BaseUseCase.UseCaseCallback<GetListFarmerByEmployeeIdUsecase.ResponseValue, GetListFarmerByEmployeeIdUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(GetListFarmerByEmployeeIdUsecase.ResponseValue successResponse) {
                        view.showListFarmer(successResponse.getListFarmer());
                        view.hideProgressBar();
                    }

                    @Override
                    public void onError(GetListFarmerByEmployeeIdUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }

    @Override
    public void loadDataOfNavigation() {
        if(UserManager.getInstance().getEmployeeUser() !=null){
            EmployeeEntity user = UserManager.getInstance().getEmployeeUser();
            view.showDataToNavigation(user.getName(), "", null, null);
        }else{
            FarmerEntity user = UserManager.getInstance().getFarmerUser();
            view.showDataToNavigation(user.getName(), user.getAddress(), user.getAvatar(), user.getRating());
        }
    }

}

