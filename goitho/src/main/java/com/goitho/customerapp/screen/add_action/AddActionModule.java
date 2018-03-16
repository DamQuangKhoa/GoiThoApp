package com.goitho.customerapp.screen.add_action;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 11/12/2017.
 */

@Module
public class AddActionModule {
    private final AddActionContract.View AddActionContractView;

    public AddActionModule(AddActionContract.View AddActionContractView) {
        this.AddActionContractView = AddActionContractView;
    }

    @Provides
    @NonNull
    AddActionContract.View provideAddActionContractView() {
        return this.AddActionContractView;
    }
}
