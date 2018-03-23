package com.goitho.customerapp.screen.register_success;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class RegisterSuccessModule {
    private final RegisterSuccessContract.View RegisterSuccessContractView;

    public RegisterSuccessModule(RegisterSuccessContract.View RegisterSuccessContractView) {
        this.RegisterSuccessContractView = RegisterSuccessContractView;
    }

    @Provides
    @NonNull
    RegisterSuccessContract.View provideRegisterSuccessContractView() {
        return this.RegisterSuccessContractView;
    }
}

