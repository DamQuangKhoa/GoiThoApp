package com.goitho.customerapp.screen.detail_order;

import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.model.offline.ImageEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.goitho.customerapp.R;
import com.goitho.customerapp.app.CoreApplication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class DetailOrderPresenter implements DetailOrderContract.Presenter {

    private final String TAG = DetailOrderPresenter.class.getName();
    private final DetailOrderContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    DetailOrderPresenter(@NonNull DetailOrderContract.View view) {
        this.view = view;

    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        view.showListImage(imageList());

    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }


    @Override
    public ArrayList<ImageEntity> imageList() {
        ArrayList<ImageEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ImageEntity imageEntity = new ImageEntity(0);
            imageEntity.setBitmap(BitmapFactory.decodeResource(CoreApplication.getInstance().getResources(),
                    R.drawable.image));
            list.add(imageEntity);
        }
        return list;

    }



}
