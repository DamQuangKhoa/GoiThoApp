package com.goitho.customerapp.screen.detail_order;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class DetailOrderModule {
    private final DetailOrderContract.View DetailOrderContractView;

    public DetailOrderModule(DetailOrderContract.View DetailOrderContractView) {
        this.DetailOrderContractView = DetailOrderContractView;
    }

    @Provides
    @NonNull
    DetailOrderContract.View provideDetailOrderContractView() {
        return this.DetailOrderContractView;
    }
}

