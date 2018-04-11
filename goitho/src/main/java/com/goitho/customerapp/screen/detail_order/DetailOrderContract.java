package com.goitho.customerapp.screen.detail_order;

import com.demo.architect.data.model.offline.ImageEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.ArrayList;

/**
 * Created by MSI on 26/11/2017.
 */

public interface DetailOrderContract {
    interface View extends BaseView<Presenter> {
        void showListImage(ArrayList<ImageEntity> mList);
        void startRatingActivity();
        void startDialogCancelOrder();
        void startDialogEditContentOrder();
        void startDialogLibraryCapture();
        void startCamera();
        void startGallery();
    }

    interface Presenter extends BasePresenter {
        ArrayList<ImageEntity> imageList();
    }
}
