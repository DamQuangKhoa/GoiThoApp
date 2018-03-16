package com.goitho.customerapp.screen.detail_diary;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 29/11/2017.
 */


@Module
public class DetailDiaryModule {
    private final DetailDiaryContract.View DetailDiaryContractView;

    public DetailDiaryModule(DetailDiaryContract.View DetailDiaryContractView) {
        this.DetailDiaryContractView = DetailDiaryContractView;
    }

    @Provides
    @NonNull
    DetailDiaryContract.View provideDetailDiaryContractView() {
        return this.DetailDiaryContractView;
    }
}
