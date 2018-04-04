package com.goitho.customerapp.screen.detail_promotion.add_action;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.PostActionProductUsecase;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.constants.Constants;

import javax.inject.Inject;

/**
 * Created by Skull on 11/12/2017.
 */

public class DetailPromotionPresenter implements DetailPromotionContract.Presenter{

    private final String TAG = DetailPromotionPresenter.class.getName();
    private final DetailPromotionContract.View view;
    private PostActionProductUsecase postActionProductUsecase;

    @Inject
    LocalRepository localRepository;

    @Inject
    DetailPromotionPresenter(@NonNull DetailPromotionContract.View view, PostActionProductUsecase postActionProductUsecase) {
        this.view = view;
        this.postActionProductUsecase = postActionProductUsecase;
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
    public void createActionProduct(String name) {
        view.showProgressBar();
        postActionProductUsecase.executeIO(new PostActionProductUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .getString(Constants.KEY_TOKEN,""),
                        name ),
                new BaseUseCase.UseCaseCallback<PostActionProductUsecase.ResponseValue, PostActionProductUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(PostActionProductUsecase.ResponseValue successResponse) {
                        view.finishActivity();
                        view.hideProgressBar();
                    }

                    @Override
                    public void onError(PostActionProductUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }
}
