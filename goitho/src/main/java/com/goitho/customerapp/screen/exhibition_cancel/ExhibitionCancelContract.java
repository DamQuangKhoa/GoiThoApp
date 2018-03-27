package com.goitho.customerapp.screen.exhibition_cancel;

import com.demo.architect.data.model.ExhibitionEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.List;

/**
 * Created by Skull on 27/11/2017.
 */

public interface ExhibitionCancelContract {
    interface View extends BaseView<Presenter> {
        void showExhibitionCancelList(List<ExhibitionEntity> list);
    }

    interface Presenter extends BasePresenter {
        List<ExhibitionEntity> list();
    }
}

