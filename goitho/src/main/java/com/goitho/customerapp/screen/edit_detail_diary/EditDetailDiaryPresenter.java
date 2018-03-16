package com.goitho.customerapp.screen.edit_detail_diary;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.model.ActionEntity;
import com.demo.architect.data.model.ActivityBuyFertilizerEntity;
import com.demo.architect.data.model.ActivityEntity;
import com.demo.architect.data.model.ActivityUseFertilizerEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.DeleteBuyFertilizerUsecase;
import com.demo.architect.domain.usecase.DeleteUseFertilizerUseCase;
import com.demo.architect.domain.usecase.GetListActionProductUsecase;
import com.demo.architect.domain.usecase.PostActivityBuyFertilizerUsecase;
import com.demo.architect.domain.usecase.PostActivityUseFertilizerUsecase;
import com.demo.architect.domain.usecase.PostActivityUsecase;
import com.demo.architect.domain.usecase.PostImageUsecase;
import com.demo.architect.domain.usecase.PutActivityBuyFertilizerUsecase;
import com.demo.architect.domain.usecase.PutActivityUseFertilizerUsecase;
import com.demo.architect.domain.usecase.PutActivityUsecase;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.manager.ActionManager;
import com.goitho.customerapp.manager.ProductManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Skull on 01/12/2017.
 */

public class EditDetailDiaryPresenter implements EditDetailDiaryContract.Presenter {

    private final String TAG = EditDetailDiaryPresenter.class.getName();
    private final EditDetailDiaryContract.View view;
    private GetListActionProductUsecase getListActionProductUsecase;
    ActivityEntity tmpEntity;
    ActivityEntity tmpOldEntity;
    private PostActivityUsecase postActivityUsecase;
    private PutActivityUsecase putActivityUsecase;
    private PostImageUsecase postImageUsecase;
    private PostActivityBuyFertilizerUsecase postActivityBuyFertilizerUsecase;
    private PutActivityBuyFertilizerUsecase putActivityBuyFertilizerUsecase;
    private PostActivityUseFertilizerUsecase postActivityUseFertilizerUsecase;
    private PutActivityUseFertilizerUsecase putActivityUseFertilizerUsecase;
    private DeleteBuyFertilizerUsecase deleteBuyFertilizerUsecase;
    private DeleteUseFertilizerUseCase deleteUseFertilizerUseCase;
    private boolean wasLoadedEntity = false;

    @Inject
    LocalRepository localRepository;

    @Inject
    EditDetailDiaryPresenter(@NonNull EditDetailDiaryContract.View view,
                             GetListActionProductUsecase getListActionProductUsecase,
                             PostActivityUsecase postActivityUsecase,
                             PutActivityUsecase putActivityUsecase,
                             PostImageUsecase postImageUsecase,
                             PostActivityBuyFertilizerUsecase postActivityBuyFertilizerUsecase,
                             PutActivityBuyFertilizerUsecase putActivityBuyFertilizerUsecase,
                             PostActivityUseFertilizerUsecase postActivityUseFertilizerUsecase,
                             PutActivityUseFertilizerUsecase putActivityUseFertilizerUsecase,
                             DeleteBuyFertilizerUsecase deleteBuyFertilizerUsecase,
                             DeleteUseFertilizerUseCase deleteUseFertilizerUseCase) {
        this.view = view;
        this.getListActionProductUsecase = getListActionProductUsecase;
        this.postActivityUsecase = postActivityUsecase;
        this.putActivityUsecase = putActivityUsecase;
        this.postImageUsecase = postImageUsecase;
        this.postActivityBuyFertilizerUsecase = postActivityBuyFertilizerUsecase;
        this.putActivityBuyFertilizerUsecase = putActivityBuyFertilizerUsecase;
        this.postActivityUseFertilizerUsecase = postActivityUseFertilizerUsecase;
        this.putActivityUseFertilizerUsecase = putActivityUseFertilizerUsecase;
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
        if (!wasLoadedEntity) {
            loadListActionProduct();
            wasLoadedEntity = true;
        }
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }

    @Override
    public void setActivityEntity(ActivityEntity entity) {
        this.tmpEntity = entity;

    }

    @Override
    public void uploadFileAndCreateNewActivity(ActivityEntity entity, ArrayList<File> listFile) {
        view.showProgressBar();
        ActivityEntity fakeEntity = entity;
        for (int i = 0; i < listFile.size(); i++) {
            postImageUsecase.executeIO(new PostImageUsecase.RequestValue(listFile.get(i)),
                    new BaseUseCase.UseCaseCallback<PostImageUsecase.ResponseValue, PostImageUsecase.ErrorValue>() {
                        @Override
                        public void onSuccess(PostImageUsecase.ResponseValue successResponse) {
                            if (!TextUtils.isEmpty(fakeEntity.getImages())) {
                                fakeEntity.setImages(fakeEntity.getImages() + "," +
                                        successResponse.getUploadEntitie().getImageUrl());
                            } else {
                                fakeEntity.setImages(successResponse.getUploadEntitie().getImageUrl());
                            }
                            String[] string = fakeEntity.getImages().split(",");
                            if (string.length == listFile.size()) {
                                createNewActivity(fakeEntity);
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

    @Override
    public void uploadFileAndEditActivity(ActivityEntity entity, ArrayList<File> listFile, ActivityEntity oldEntity) {
        view.showProgressBar();
        ActivityEntity fakeEntity = entity;
        int imageNumber = !TextUtils.isEmpty(entity.getImages()) ? entity.getImages().split(",").length : 0;
        for (int i = 0; i < listFile.size(); i++) {
            postImageUsecase.executeIO(new PostImageUsecase.RequestValue(listFile.get(i)),
                    new BaseUseCase.UseCaseCallback<PostImageUsecase.ResponseValue, PostImageUsecase.ErrorValue>() {
                        @Override
                        public void onSuccess(PostImageUsecase.ResponseValue successResponse) {
                            if (!TextUtils.isEmpty(fakeEntity.getImages())) {
                                fakeEntity.setImages(fakeEntity.getImages() + "," +
                                        successResponse.getUploadEntitie().getImageUrl());
                            } else {
                                fakeEntity.setImages(successResponse.getUploadEntitie().getImageUrl());
                            }
                            String[] string = fakeEntity.getImages().split(",");
                            if (string.length - imageNumber == listFile.size()) {
                                editActivity(fakeEntity, oldEntity);
                            }
                        }

                        @Override
                        public void onError(PostImageUsecase.ErrorValue errorResponse) {
                            String[] string = fakeEntity.getImages().split(",");
                            if (string.length - imageNumber == listFile.size()) {
                                view.hideProgressBar();
                                view.showError();
                            }
                        }
                    });
        }

    }

    public void loadListActionProduct() {
        view.showProgressBar();
        getListActionProductUsecase.executeIO(new GetListActionProductUsecase.RequestValue(
                SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).getString(Constants.KEY_TOKEN,"")),
                new BaseUseCase.UseCaseCallback<GetListActionProductUsecase.ResponseValue, GetListActionProductUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(GetListActionProductUsecase.ResponseValue successResponse) {
                        List<ActionEntity> listActionEntities = successResponse.getListActionProduct();
                        Collections.sort(listActionEntities, new Comparator<ActionEntity>() {
                            @Override
                            public int compare(ActionEntity o1, ActionEntity o2) {
                                return o2.getCreatedAtDate().compareTo(o1.getCreatedAtDate()); //Sort DESC
                            }
                        });

                        ActionManager.getInstance().setListAction(listActionEntities);
                        view.showProductAndActon(ProductManager.getInstance().getStringListProduct(),
                                ActionManager.getInstance().getStringListAction());
                        if (tmpEntity != null)
                            view.showDetailDiary(tmpEntity);
                        view.hideProgressBar();
                    }

                    @Override
                    public void onError(GetListActionProductUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        //view.showError();
                    }
                });
    }

    @Override
    public void createNewActivity(ActivityEntity entity) {
        view.showProgressBar();
        postActivityUsecase.executeIO(new PostActivityUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .getString(Constants.KEY_TOKEN, ""),
                        entity
                ),
                new BaseUseCase.UseCaseCallback<PostActivityUsecase.ResponseValue, PostActivityUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(PostActivityUsecase.ResponseValue successResponse) {
                        tmpEntity = successResponse.getActivityEntities();
                        if (entity.getActivityUseFertilizerEntity() != null) {
                            entity.getActivityUseFertilizerEntity().setActivityId(
                                    successResponse.getActivityEntities().getId());
                            createActivityUseFertilizer(entity.getActivityUseFertilizerEntity());
                        }
                        if (entity.getActivityBuyFertilizerEntity() != null) {
                            entity.getActivityBuyFertilizerEntity().setActivityId(
                                    successResponse.getActivityEntities().getId());
                            createActivityBuyFertilizer(entity.getActivityBuyFertilizerEntity());
                        }
                        if (entity.getActivityUseFertilizerEntity() == null &&
                                entity.getActivityBuyFertilizerEntity() == null) {
                            view.setResultNewActivity(successResponse.getActivityEntities());
                            view.hideProgressBar();
                            view.finishActivity();
                        }
                    }

                    @Override
                    public void onError(PostActivityUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }

    @Override
    public void editActivity(ActivityEntity entity, ActivityEntity oldEntity) {
        view.showProgressBar();
        postActivityUsecase.executeIO(new PostActivityUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .getString(Constants.KEY_TOKEN, ""),
                        entity
                ),
                new BaseUseCase.UseCaseCallback<PostActivityUsecase.ResponseValue, PostActivityUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(PostActivityUsecase.ResponseValue successResponse) {
                        tmpEntity = successResponse.getActivityEntities();
                        if (entity.getActivityUseFertilizerEntity() != null) {
                            entity.getActivityUseFertilizerEntity().setActivityId(
                                    successResponse.getActivityEntities().getId());
                            createActivityUseFertilizer(entity.getActivityUseFertilizerEntity());
                        }
                        if (entity.getActivityBuyFertilizerEntity() != null) {
                            entity.getActivityBuyFertilizerEntity().setActivityId(
                                    successResponse.getActivityEntities().getId());
                            createActivityBuyFertilizer(entity.getActivityBuyFertilizerEntity());
                        }
                        ActivityEntity newEntity = successResponse.getActivityEntities();
                        oldEntity.setParentId(successResponse.getActivityEntities().getId());
                        tmpOldEntity = oldEntity;
                        if (entity.getActivityUseFertilizerEntity() == null &&
                                entity.getActivityBuyFertilizerEntity() == null) {
                            view.setResultOldAndNewActivity(oldEntity, newEntity);
                        }
                        putActivityUsecase.executeIO(new PutActivityUsecase.RequestValue(
                                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                                .getString(Constants.KEY_TOKEN, ""), oldEntity.getId(),
                                        oldEntity
                                ),
                                new BaseUseCase.UseCaseCallback<PutActivityUsecase.ResponseValue, PutActivityUsecase.ErrorValue>() {
                                    @Override
                                    public void onSuccess(PutActivityUsecase.ResponseValue successResponse) {

                                        if (oldEntity.getActivityUseFertilizerEntity() != null) {
                                            if (!TextUtils.isEmpty(oldEntity.getActivityUseFertilizerEntity().getId())) {
                                                deleteUseActivity(oldEntity.getActivityUseFertilizerEntity().getId());
                                            }
                                        }
                                        if (oldEntity.getActivityBuyFertilizerEntity() != null) {
                                            if (!TextUtils.isEmpty(oldEntity.getActivityBuyFertilizerEntity().getId())) {
                                                deleteBuyFertilizer(oldEntity.getActivityBuyFertilizerEntity().getId());
                                            }
                                        }
                                        view.hideProgressBar();
                                        if (oldEntity.getActivityUseFertilizerEntity() == null &&
                                                oldEntity.getActivityBuyFertilizerEntity() == null) {
                                            view.finishActivity();
                                        }
                                    }

                                    @Override
                                    public void onError(PutActivityUsecase.ErrorValue errorResponse) {
                                        view.hideProgressBar();
                                        view.showError();
                                    }
                                });
                    }

                    @Override
                    public void onError(PostActivityUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });

    }

    @Override
    public void createActivityBuyFertilizer(ActivityBuyFertilizerEntity entity) {
        view.showProgressBar();
        postActivityBuyFertilizerUsecase.executeIO(new PostActivityBuyFertilizerUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .getString(Constants.KEY_TOKEN, ""),
                        entity
                ),
                new BaseUseCase.UseCaseCallback<PostActivityBuyFertilizerUsecase.ResponseValue, PostActivityBuyFertilizerUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(PostActivityBuyFertilizerUsecase.ResponseValue successResponse) {
                        tmpEntity.setActivityBuyFertilizerEntity(successResponse.getActivityBuyFertilizerEntities());
                        if (tmpOldEntity == null) {
                            view.setResultNewActivity(tmpEntity);
                        } else {
                            view.setResultOldAndNewActivity(tmpOldEntity, tmpEntity);
                        }
                        view.hideProgressBar();
                        view.finishActivity();
                    }

                    @Override
                    public void onError(PostActivityBuyFertilizerUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }

    @Override
    public void createActivityUseFertilizer(ActivityUseFertilizerEntity entity) {
        view.showProgressBar();
        postActivityUseFertilizerUsecase.executeIO(new PostActivityUseFertilizerUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .getString(Constants.KEY_TOKEN, ""),
                        entity
                ),
                new BaseUseCase.UseCaseCallback<PostActivityUseFertilizerUsecase.ResponseValue, PostActivityUseFertilizerUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(PostActivityUseFertilizerUsecase.ResponseValue successResponse) {
                        tmpEntity.setActivityUseFertilizerEntity(successResponse.getActivityUseFertilizerEntities());
                        if (tmpOldEntity == null) {
                            view.setResultNewActivity(tmpEntity);
                        } else {
                            view.setResultOldAndNewActivity(tmpOldEntity, tmpEntity);
                        }
                        view.hideProgressBar();
                        view.finishActivity();
                    }

                    @Override
                    public void onError(PostActivityUseFertilizerUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }

    @Override
    public void updateActivityBuyFertilizer(ActivityBuyFertilizerEntity entity) {
        view.showProgressBar();
        putActivityBuyFertilizerUsecase.executeIO(new PutActivityBuyFertilizerUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .getString(Constants.KEY_TOKEN, ""), entity.getId(),
                        entity
                ),
                new BaseUseCase.UseCaseCallback<PutActivityBuyFertilizerUsecase.ResponseValue, PutActivityBuyFertilizerUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(PutActivityBuyFertilizerUsecase.ResponseValue successResponse) {

                        view.hideProgressBar();
                    }

                    @Override
                    public void onError(PutActivityBuyFertilizerUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }

    @Override
    public void updateActivityUseFertilizer(ActivityUseFertilizerEntity entity) {
        view.showProgressBar();
        putActivityUseFertilizerUsecase.executeIO(new PutActivityUseFertilizerUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .getString(Constants.KEY_TOKEN, ""), entity.getId(),
                        entity
                ),
                new BaseUseCase.UseCaseCallback<PutActivityUseFertilizerUsecase.ResponseValue, PutActivityUseFertilizerUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(PutActivityUseFertilizerUsecase.ResponseValue successResponse) {

                        view.hideProgressBar();
                    }

                    @Override
                    public void onError(PutActivityUseFertilizerUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }

    public void deleteBuyFertilizer(String id) {
        deleteBuyFertilizerUsecase.executeIO(new DeleteBuyFertilizerUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .getString(Constants.KEY_TOKEN, ""),
                        id
                ),
                new BaseUseCase.UseCaseCallback<DeleteBuyFertilizerUsecase.ResponseValue, DeleteBuyFertilizerUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(DeleteBuyFertilizerUsecase.ResponseValue successResponse) {
                        view.hideProgressBar();
                        view.finishActivity();
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
                                .getString(Constants.KEY_TOKEN, ""),
                        id
                ),
                new BaseUseCase.UseCaseCallback<DeleteUseFertilizerUseCase.ResponseValue, DeleteUseFertilizerUseCase.ErrorValue>() {
                    @Override
                    public void onSuccess(DeleteUseFertilizerUseCase.ResponseValue successResponse) {

                        view.hideProgressBar();
                        view.finishActivity();
                    }

                    @Override
                    public void onError(DeleteUseFertilizerUseCase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }
}

