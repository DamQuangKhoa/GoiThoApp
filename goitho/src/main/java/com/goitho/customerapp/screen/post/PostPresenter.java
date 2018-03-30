package com.goitho.customerapp.screen.post;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.model.PostEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class PostPresenter implements PostContract.Presenter {

    private final String TAG = PostPresenter.class.getName();
    private final PostContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    PostPresenter(@NonNull PostContract.View view) {
        this.view = view;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        view.showContent();
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }

}
