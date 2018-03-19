package com.goitho.customerapp.screen.register;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class RegisterModule {
    private final RegisterContract.View RegisterContractView;

    public RegisterModule(RegisterContract.View RegisterContractView) {
        this.RegisterContractView = RegisterContractView;
    }

    @Provides
    @NonNull
    RegisterContract.View provideRegisterContractView() {
        return this.RegisterContractView;
    }
}

