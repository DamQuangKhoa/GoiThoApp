package com.goitho.customerapp.screen.diary;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 27/11/2017.
 */


@Module
public class DiaryModule {
    private final DiaryContract.View DiaryContractView;

    public DiaryModule(DiaryContract.View DiaryContractView) {
        this.DiaryContractView = DiaryContractView;
    }

    @Provides
    @NonNull
    DiaryContract.View provideDiaryContractView() {
        return this.DiaryContractView;
    }
}

