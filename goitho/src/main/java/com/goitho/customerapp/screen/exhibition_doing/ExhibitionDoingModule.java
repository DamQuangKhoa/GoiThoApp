package com.goitho.customerapp.screen.exhibition_doing;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 27/11/2017.
 */


@Module
public class ExhibitionDoingModule {
    private final ExhibitionDoingContract.View ExhibitionDoingContractView;

    public ExhibitionDoingModule(ExhibitionDoingContract.View ExhibitionDoingContractView) {
        this.ExhibitionDoingContractView = ExhibitionDoingContractView;
    }

    @Provides
    @NonNull
    ExhibitionDoingContract.View provideExhibitionDoingContractView() {
        return this.ExhibitionDoingContractView;
    }
}

