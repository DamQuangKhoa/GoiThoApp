package com.goitho.customerapp.screen.rating;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class RatingModule {
    private final RatingContract.View RatingContractView;

    public RatingModule(RatingContract.View RatingContractView) {
        this.RatingContractView = RatingContractView;
    }

    @Provides
    @NonNull
    RatingContract.View provideRatingContractView() {
        return this.RatingContractView;
    }
}

