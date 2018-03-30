package com.goitho.customerapp.screen.home;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.model.PostEntity;
import com.demo.architect.data.model.RatingEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class HomePresenter implements HomeContract.Presenter {

    private final String TAG = HomePresenter.class.getName();
    private final HomeContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    HomePresenter(@NonNull HomeContract.View view) {
        this.view = view;

    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        view.showRatingList(ratingList());
        view.showBlogList(blogList());
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }

    @Override
    public ArrayList<RatingEntity> ratingList() {
        ArrayList<RatingEntity> list = new ArrayList<>();
        for (int i=0; i<10; i++){
            list.add(new RatingEntity());
        }
        return list;
    }

    @Override
    public List<PostEntity> blogList() {
        List<PostEntity> list = new ArrayList<>();
        for (int i = 0; i<3; i++){
            list.add(new PostEntity("Trang trí nội thất theo phong cách châu Âu ", "Sep 10, 2017",
                    1237, 815));
        }
        return list;
    }
}
