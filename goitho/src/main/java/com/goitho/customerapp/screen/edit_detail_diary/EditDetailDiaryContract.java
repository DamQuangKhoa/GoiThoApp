package com.goitho.customerapp.screen.edit_detail_diary;

import com.demo.architect.data.model.ActivityBuyFertilizerEntity;
import com.demo.architect.data.model.ActivityEntity;
import com.demo.architect.data.model.ActivityUseFertilizerEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Skull on 01/12/2017.
 */

public interface EditDetailDiaryContract {
    interface View extends BaseView<Presenter> {
        void showProductAndActon(ArrayList<String> listProduct, ArrayList<String> listAction);
        void showDetailDiary(ActivityEntity entity);
        void showActivityBuyFertilizer(ActivityBuyFertilizerEntity entity);
        void showActivityUseFertilizer(ActivityUseFertilizerEntity entity);
        void saveActivityBuyFertilizer(ActivityBuyFertilizerEntity entity);
        void saveActivityUseFertilizer(ActivityUseFertilizerEntity entity);
        void showError();
        void finishActivity();
        void setResultNewActivity(ActivityEntity entity);
        void setResultOldAndNewActivity(ActivityEntity oldEntity, ActivityEntity newEntity);
    }

    interface Presenter extends BasePresenter {
        void setActivityEntity(ActivityEntity entity);
        void uploadFileAndCreateNewActivity(ActivityEntity entity, ArrayList<File> listFile);
        void uploadFileAndEditActivity(ActivityEntity entity, ArrayList<File> listFile, ActivityEntity oldEntity);
        void createNewActivity(ActivityEntity entity);
        void editActivity(ActivityEntity entity, ActivityEntity oldEntity);
        void createActivityBuyFertilizer(ActivityBuyFertilizerEntity entity);
        void createActivityUseFertilizer(ActivityUseFertilizerEntity entity);
        void updateActivityBuyFertilizer(ActivityBuyFertilizerEntity entity);
        void updateActivityUseFertilizer(ActivityUseFertilizerEntity entity);

    }
}
