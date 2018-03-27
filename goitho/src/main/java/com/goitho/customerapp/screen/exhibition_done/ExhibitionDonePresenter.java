package com.goitho.customerapp.screen.exhibition_done;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.model.ExhibitionEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Skull on 27/11/2017.
 */

public class ExhibitionDonePresenter implements ExhibitionDoneContract.Presenter{

    private final String TAG = ExhibitionDonePresenter.class.getName();
    private final ExhibitionDoneContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    ExhibitionDonePresenter(@NonNull ExhibitionDoneContract.View view) {
        this.view = view;

    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        view.showExhibitionDoneList(list());
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }


    @Override
    public List<ExhibitionEntity> list() {
        List<ExhibitionEntity> list = new ArrayList<>();
        for(int i =0; i< 2; i++){
            list.add(new ExhibitionEntity("Lắp hai điều hoà tầng ba, bốn. " +
                    "Bảo dưỡng một điều hoà tầng 1", "3:30pm, \n 24 th12 2017", 2,
                    3, ""));
        }
        for(int i =0; i< 10; i++){
            list.add(new ExhibitionEntity("Lắp hai điều hoà tầng ba, bốn. " +
                    "Bảo dưỡng một điều hoà tầng 1", "3:30pm, \n 24 th12 2017", 2,
                    0, ""));
        }
        return list;
    }
}
