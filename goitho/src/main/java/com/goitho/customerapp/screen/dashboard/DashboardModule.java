package com.goitho.customerapp.screen.dashboard;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 14/12/2017.
 */

@Module
public class DashboardModule {
    private final DashboardContract.View DashboardContractView;

    public DashboardModule(DashboardContract.View DashboardContractView) {
        this.DashboardContractView = DashboardContractView;
    }

    @Provides
    @NonNull
    DashboardContract.View provideDashboardContractView() {
        return this.DashboardContractView;
    }
}
