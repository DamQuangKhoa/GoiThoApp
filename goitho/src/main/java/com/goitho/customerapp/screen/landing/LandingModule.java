package com.goitho.customerapp.screen.landing;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class LandingModule {
    private final LandingContract.View SplashContractView;

    public LandingModule(LandingContract.View SplashContractView) {
        this.SplashContractView = SplashContractView;
    }

    @Provides
    @NonNull
    LandingContract.View provideSplashContractView() {
        return this.SplashContractView;
    }
}

