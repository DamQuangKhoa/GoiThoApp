package com.goitho.customerapp.screen.detail_diary;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.model.ActivityEntity;
import com.demo.architect.data.model.offline.DetailDiaryEntity;
import com.demo.architect.data.model.offline.DiaryEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.DeleteActivityUsecase;
import com.demo.architect.domain.usecase.DeleteBuyFertilizerUsecase;
import com.demo.architect.domain.usecase.DeleteUseFertilizerUseCase;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.constants.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Skull on 29/11/2017.
 */

public class DetailDiaryPresenter implements DetailDiaryContract.Presenter{

    private final String TAG = DetailDiaryPresenter.class.getName();
    private final DetailDiaryContract.View view;
    ArrayList<DetailDiaryEntity> list = new ArrayList<>();
    private int position = 0;
    private DiaryEntity diaryEntity;

    private DeleteActivityUsecase deleteActivityUsecase;
    private DeleteBuyFertilizerUsecase deleteBuyFertilizerUsecase;
    private DeleteUseFertilizerUseCase deleteUseFertilizerUseCase;

    @Inject
    LocalRepository localRepository;

    @Inject
    DetailDiaryPresenter(@NonNull DetailDiaryContract.View view,
                         DeleteActivityUsecase deleteActivityUsecase,
                         DeleteBuyFertilizerUsecase deleteBuyFertilizerUsecase,
                         DeleteUseFertilizerUseCase deleteUseFertilizerUseCase) {
        this.view = view;
        this.deleteActivityUsecase = deleteActivityUsecase;
        this.deleteBuyFertilizerUsecase = deleteBuyFertilizerUsecase;
        this.deleteUseFertilizerUseCase = deleteUseFertilizerUseCase;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }

    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        if (diaryEntity != null) {
            view.showDetailDiary(diaryEntity.getDetail());
        }
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");

    }

    public void setDiaryEntity(DiaryEntity diaryEntity) {
        this.diaryEntity = diaryEntity;
    }
    boolean isDeleting = false;
    @Override
    public void deleteActivity(ActivityEntity entity) {
        view.showProgressBar();
        if(!isDeleting) {
            isDeleting = true;
            deleteActivityUsecase.executeIO(new DeleteActivityUsecase.RequestValue(
                            SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                    .getString(Constants.KEY_TOKEN,""),
                            entity.getId()
                    ),
                    new BaseUseCase.UseCaseCallback<DeleteActivityUsecase.ResponseValue, DeleteActivityUsecase.ErrorValue>() {
                        @Override
                        public void onSuccess(DeleteActivityUsecase.ResponseValue successResponse) {
                            if(entity.getActivityBuyFertilizerEntity() != null) {
                                deleteBuyFertilizer(entity.getActivityBuyFertilizerEntity().getId());
                            }

                            if(entity.getActivityUseFertilizerEntity() != null) {
                                deleteUseActivity(entity.getActivityUseFertilizerEntity().getId());
                            }
                            for (int i = 0; i< diaryEntity.getDetail().size(); i++) {
                                if(diaryEntity.getDetail().get(i).getId().equals(entity.getId())){
                                    diaryEntity.getDetail().remove(i);
                                    break;
                                }
                            }
                            isDeleting = false;
                            view.showDetailDiary(diaryEntity.getDetail());
                            view.hideProgressBar();
                        }

                        @Override
                        public void onError(DeleteActivityUsecase.ErrorValue errorResponse) {
                            isDeleting = false;
                            view.hideProgressBar();
                            view.showError();
                        }
                    });
        }
    }

    @Override
    public void setListActivity(List<ActivityEntity> list) {
        diaryEntity.setDetail(list);
    }

    public void deleteBuyFertilizer(String id) {
        deleteBuyFertilizerUsecase.executeIO(new DeleteBuyFertilizerUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .getString(Constants.KEY_TOKEN,""),
                       id
                ),
                new BaseUseCase.UseCaseCallback<DeleteBuyFertilizerUsecase.ResponseValue, DeleteBuyFertilizerUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(DeleteBuyFertilizerUsecase.ResponseValue successResponse) {

                        view.hideProgressBar();
                    }

                    @Override
                    public void onError(DeleteBuyFertilizerUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }

    public void deleteUseActivity(String id) {
        deleteUseFertilizerUseCase.executeIO(new DeleteUseFertilizerUseCase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .getString(Constants.KEY_TOKEN,""),
                        id
                ),
                new BaseUseCase.UseCaseCallback<DeleteUseFertilizerUseCase.ResponseValue, DeleteUseFertilizerUseCase.ErrorValue>() {
                    @Override
                    public void onSuccess(DeleteUseFertilizerUseCase.ResponseValue successResponse) {

                        view.hideProgressBar();
                    }

                    @Override
                    public void onError(DeleteUseFertilizerUseCase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }
}
