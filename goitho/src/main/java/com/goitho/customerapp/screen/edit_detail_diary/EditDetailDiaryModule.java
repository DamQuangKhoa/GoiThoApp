package com.goitho.customerapp.screen.edit_detail_diary;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 01/12/2017.
 */
@Module
public class EditDetailDiaryModule {
    private final EditDetailDiaryContract.View EditDetailDiaryContractView;

    public EditDetailDiaryModule(EditDetailDiaryContract.View EditDetailDiaryContractView) {
        this.EditDetailDiaryContractView = EditDetailDiaryContractView;
    }

    @Provides
    @NonNull
    EditDetailDiaryContract.View provideEditDetailDiaryContractView() {
        return this.EditDetailDiaryContractView;
    }
}

