package com.goitho.customerapp.screen.dashboard;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 14/12/2017.
 */

@Module
public class DashboardModule {
    private final DashboardContract.View HistoryContractView;

    public DashboardModule(DashboardContract.View HistoryContractView) {
        this.HistoryContractView = HistoryContractView;
    }

    @Provides
    @NonNull
    DashboardContract.View provideHistoryContractView() {
        return this.HistoryContractView;
    }
}
