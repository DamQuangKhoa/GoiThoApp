package com.goitho.customerapp.screen.exhibition_done;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 27/11/2017.
 */


@Module
public class ExhibitionDoneModule {
    private final ExhibitionDoneContract.View ExhibitionDoneContractView;

    public ExhibitionDoneModule(ExhibitionDoneContract.View ExhibitionDoneContractView) {
        this.ExhibitionDoneContractView = ExhibitionDoneContractView;
    }

    @Provides
    @NonNull
    ExhibitionDoneContract.View provideExhibitionDoneContractView() {
        return this.ExhibitionDoneContractView;
    }
}

