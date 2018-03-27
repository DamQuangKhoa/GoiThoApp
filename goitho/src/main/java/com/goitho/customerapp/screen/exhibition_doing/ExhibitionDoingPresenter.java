package com.goitho.customerapp.screen.exhibition_doing;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.demo.architect.data.helper.DateHelper;
import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.model.ActivityEntity;
import com.demo.architect.data.model.EmployeeEntity;
import com.demo.architect.data.model.ExhibitionEntity;
import com.demo.architect.data.model.FarmerEntity;
import com.demo.architect.data.model.offline.DiaryEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.GetListActionProductUsecase;
import com.demo.architect.domain.usecase.GetListActivityUsecase;
import com.demo.architect.domain.usecase.GetProductUsecase;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.manager.ActionManager;
import com.goitho.customerapp.manager.ProductManager;
import com.goitho.customerapp.manager.UserManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

/**
 * Created by Skull on 27/11/2017.
 */

public class ExhibitionDoingPresenter implements ExhibitionDoingContract.Presenter{

    private final String TAG = ExhibitionDoingPresenter.class.getName();
    private final ExhibitionDoingContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    ExhibitionDoingPresenter(@NonNull ExhibitionDoingContract.View view) {
        this.view = view;

    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        view.showExhibitionDoingList(list());
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }


    @Override
    public List<ExhibitionEntity> list() {
        List<ExhibitionEntity> list = new ArrayList<>();
        for(int i =0; i< 10; i++){
            list.add(new ExhibitionEntity("Lắp hai điều hoà tầng ba, bốn. " +
                    "Bảo dưỡng một điều hoà tầng 1", "3:30pm, \n 24 th12 2017", 1,
                    0, ""));
        }
        return list;
    }
}
