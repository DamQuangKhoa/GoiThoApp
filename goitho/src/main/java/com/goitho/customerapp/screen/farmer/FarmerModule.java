package com.goitho.customerapp.screen.farmer;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 27/11/2017.
 */


@Module
public class FarmerModule {
    private final FarmerContract.View FarmerContractView;

    public FarmerModule(FarmerContract.View FarmerContractView) {
        this.FarmerContractView = FarmerContractView;
    }

    @Provides
    @NonNull
    FarmerContract.View provideFarmerContractView() {
        return this.FarmerContractView;
    }
}

