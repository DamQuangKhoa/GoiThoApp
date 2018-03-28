package com.goitho.customerapp.screen.detail_order;

import android.widget.ImageView;

import com.demo.architect.data.model.NotificationEntity;
import com.demo.architect.data.model.offline.ImageEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MSI on 26/11/2017.
 */

public interface DetailOrderContract {
    interface View extends BaseView<Presenter> {
        void showListImage(ArrayList<ImageEntity> mList);
        void startRatingActivity();
    }

    interface Presenter extends BasePresenter {
        ArrayList<ImageEntity> imageList();
    }
}
