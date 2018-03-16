package com.goitho.customerapp.screen.history;

import com.demo.architect.data.model.ActivityEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.List;

/**
 * Created by Skull on 14/12/2017.
 */

public interface HistoryContract {
    interface View extends BaseView<Presenter> {
        void showHistory(List<ActivityEntity> list);
        void showError();
    }

    interface Presenter extends BasePresenter {
        void loadHistory(String id);
    }
}
