package com.goitho.customerapp.screen.history;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 14/12/2017.
 */

@Module
public class HistoryModule {
    private final HistoryContract.View HistoryContractView;

    public HistoryModule(HistoryContract.View HistoryContractView) {
        this.HistoryContractView = HistoryContractView;
    }

    @Provides
    @NonNull
    HistoryContract.View provideHistoryContractView() {
        return this.HistoryContractView;
    }
}
