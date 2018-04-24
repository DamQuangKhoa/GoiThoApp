package com.goitho.customerapp.screen.promotion;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.GetListPromotionsUsecase;
import com.goitho.customerapp.manager.ListPromotionsManager;
import com.goitho.customerapp.manager.UserManager;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class PromotionPresenter implements PromotionContract.Presenter {

    private final String TAG = PromotionPresenter.class.getName();
    private final PromotionContract.View view;
    private final GetListPromotionsUsecase getListPromotionsUsecase;

    @Inject
    LocalRepository localRepository;

    @Inject
    PromotionPresenter(@NonNull PromotionContract.View view, GetListPromotionsUsecase getListPromotionsUsecase) {
        this.view = view;
        this.getListPromotionsUsecase = getListPromotionsUsecase;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        getListPromotion(0,20);
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }

    @Override
    public void getListPromotion(int loaded, int perload) {
        String userId = UserManager.getInstance().getUser() != null ?UserManager.getInstance().getUser().getUserId():
                null;
        getListPromotionsUsecase.executeIO(new GetListPromotionsUsecase.RequestValue(userId, loaded, perload),
                new BaseUseCase.UseCaseCallback
                        <GetListPromotionsUsecase.ResponseValue, GetListPromotionsUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(GetListPromotionsUsecase.ResponseValue successResponse) {
                        ListPromotionsManager.getInstance().setListPromotions(successResponse.getListPromotionEntity().getListPromotion());
                        view.showPromotionList();
                    }

                    @Override
                    public void onError(GetListPromotionsUsecase.ErrorValue errorResponse) {
                        view.showError();
                    }
                });
    }
}
