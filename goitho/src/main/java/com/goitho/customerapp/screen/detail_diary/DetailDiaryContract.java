package com.goitho.customerapp.screen.detail_diary;

import com.demo.architect.data.model.ActivityEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.List;

/**
 * Created by Skull on 29/11/2017.
 */

public interface DetailDiaryContract {
    interface View extends BaseView<Presenter> {
        void showDetailDiary(List<ActivityEntity> list);
        void showError();
    }

    interface Presenter extends BasePresenter {
        void deleteActivity(ActivityEntity entity);
        void setListActivity(List<ActivityEntity> list);
    }
}

