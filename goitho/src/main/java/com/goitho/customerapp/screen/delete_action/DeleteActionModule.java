package com.goitho.customerapp.screen.delete_action;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 11/12/2017.
 */

@Module
public class DeleteActionModule {
    private final DeleteActionContract.View DeleteActionContractView;

    public DeleteActionModule(DeleteActionContract.View DeleteActionContractView) {
        this.DeleteActionContractView = DeleteActionContractView;
    }

    @Provides
    @NonNull
    DeleteActionContract.View provideDeleteActionContractView() {
        return this.DeleteActionContractView;
    }
}