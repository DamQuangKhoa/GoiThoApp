package com.goitho.customerapp.screen.exhibition_cancel;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 27/11/2017.
 */


@Module
public class ExhibitionCancelModule {
    private final ExhibitionCancelContract.View ExhibitionCancelContractView;

    public ExhibitionCancelModule(ExhibitionCancelContract.View ExhibitionCancelContractView) {
        this.ExhibitionCancelContractView = ExhibitionCancelContractView;
    }

    @Provides
    @NonNull
    ExhibitionCancelContract.View provideExhibitionCancelContractView() {
        return this.ExhibitionCancelContractView;
    }
}

