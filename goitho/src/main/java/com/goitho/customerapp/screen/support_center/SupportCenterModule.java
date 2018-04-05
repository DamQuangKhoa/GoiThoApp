package com.goitho.customerapp.screen.support_center;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class SupportCenterModule {
    private final SupportCenterContract.View SupportCenterContractView;

    public SupportCenterModule(SupportCenterContract.View SupportCenterContractView) {
        this.SupportCenterContractView = SupportCenterContractView;
    }

    @Provides
    @NonNull
    SupportCenterContract.View provideSupportCenterContractView() {
        return this.SupportCenterContractView;
    }
}

