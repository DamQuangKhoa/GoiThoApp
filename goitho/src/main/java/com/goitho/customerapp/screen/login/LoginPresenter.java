package com.goitho.customerapp.screen.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.GetListEmployeeUseCase;
import com.demo.architect.domain.usecase.GetListFarmerUsecase;
import com.demo.architect.domain.usecase.GetProductUsecase;
import com.demo.architect.domain.usecase.LoginEmployeeUsecase;
import com.demo.architect.domain.usecase.LoginFarmerUsecase;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.manager.FarmerManager;
import com.goitho.customerapp.manager.ProductManager;
import com.goitho.customerapp.manager.UserManager;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class LoginPresenter implements LoginContract.Presenter{

    private final String TAG = LoginPresenter.class.getName();
    private final LoginContract.View view;
    private LoginFarmerUsecase loginFarmerUsecase;
    private LoginEmployeeUsecase loginEmployeeUsecase;
    private GetListFarmerUsecase getListFarmerUseCase;
    private GetListEmployeeUseCase getListEmployeeUseCase;
    private GetProductUsecase getProductUsecase;

    @Inject
    LocalRepository localRepository;

    @Inject
    LoginPresenter(@NonNull LoginContract.View view, LoginFarmerUsecase loginFarmerUsecase,
                   LoginEmployeeUsecase loginEmployeeUsecase,
                   GetListFarmerUsecase getListFarmerUseCase,
                   GetListEmployeeUseCase getListEmployeeUseCase,
                   GetProductUsecase getProductUsecase) {
        this.view = view;
        this.loginFarmerUsecase = loginFarmerUsecase;
        this.loginEmployeeUsecase = loginEmployeeUsecase;
        this.getListFarmerUseCase = getListFarmerUseCase;
        this.getListEmployeeUseCase = getListEmployeeUseCase;
        this.getProductUsecase = getProductUsecase;
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

    public void loginFarmer(String email, String password){
        view.showProgressBar();
        loginFarmerUsecase.executeIO(new LoginFarmerUsecase.RequestValue(email, password),
                new BaseUseCase.UseCaseCallback<LoginFarmerUsecase.ResponseValue, LoginFarmerUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(LoginFarmerUsecase.ResponseValue successResponse) {
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .pushString(Constants.KEY_TOKEN,
                                        successResponse.getEntity().getAccessToken());
                        UserManager.getInstance().setFarmerUser(successResponse.getEntity());
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .pushEmployeeObject(null);
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .pushFarmerObject(successResponse.getEntity());
                        loadListProduct();
                        loadListFarmer();
                        view.hideProgressBar();
                        view.startDiaryActivity(UserManager.getInstance().getFarmerUser().getId());
                    }

                    @Override
                    public void onError(LoginFarmerUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }

    public void loginEmployee(String email, String password){
        view.showProgressBar();
        loginEmployeeUsecase.executeIO(new LoginEmployeeUsecase.RequestValue(email, password),
                new BaseUseCase.UseCaseCallback<LoginEmployeeUsecase.ResponseValue, LoginEmployeeUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(LoginEmployeeUsecase.ResponseValue successResponse) {

                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .pushString(Constants.KEY_TOKEN, successResponse.getEntity().getAccessToken());
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .pushEmployeeObject(successResponse.getEntity());
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .pushFarmerObject(null);
                        UserManager.getInstance().setEmployeeUser(successResponse.getEntity());
                        loadListProduct();
                        loadListFarmer();
                        view.hideProgressBar();
                        view.startFarmerActivity();
//                        getListEmployeeUseCase.executeIO(new GetListEmployeeUseCase.RequestValue(successResponse.getToken()),
//                                new BaseUseCase.UseCaseCallback<GetListEmployeeUseCase.ResponseValue, GetListEmployeeUseCase.ErrorValue>() {
//                                    @Override
//                                    public void onSuccess(GetListEmployeeUseCase.ResponseValue successResponse) {
//                                        List<EmployeeEntity> employeeEntities =successResponse.getListEmployee();
//                                        for (EmployeeEntity entity: employeeEntities) {
//                                            if(entity.getUsername().equals(email) ){
//                                                UserManager.getInstance().setEmployeeUser(entity);
//                                                break;
//                                            }
//                                        }
//                                        view.hideProgressBar();
//                                        view.startFarmerActivity();
//                                    }
//
//                                    @Override
//                                    public void onError(GetListEmployeeUseCase.ErrorValue errorResponse) {
//                                        view.hideProgressBar();
//                                        view.showError();
//                                    }
//
//                                });

                    }

                    @Override
                    public void onError(LoginEmployeeUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }

    private void loadListProduct(){
        getProductUsecase.executeIO(new GetProductUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).getString(Constants.KEY_TOKEN,"")
                ),
                new BaseUseCase.UseCaseCallback<GetProductUsecase.ResponseValue, GetProductUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(GetProductUsecase.ResponseValue successResponse) {
                        ProductManager.getInstance().setList(successResponse.getProductEntity());
                    }

                    @Override
                    public void onError(GetProductUsecase.ErrorValue errorResponse) {
                        //view.showError();
                    }
                });
    }

    private void loadListFarmer(){
        getListFarmerUseCase.executeIO(new GetListFarmerUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).getString(Constants.KEY_TOKEN,"")
                ),
                new BaseUseCase.UseCaseCallback<GetListFarmerUsecase.ResponseValue, GetListFarmerUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(GetListFarmerUsecase.ResponseValue successResponse) {
                        FarmerManager.getInstance().setList(successResponse.getListFarmer());

                    }

                    @Override
                    public void onError(GetListFarmerUsecase.ErrorValue errorResponse) {

                    }
                });
    }
}
