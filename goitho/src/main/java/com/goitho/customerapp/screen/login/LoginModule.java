package com.goitho.customerapp.screen.login;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class LoginModule {
    private final LoginContract.View LoginContractView;

    public LoginModule(LoginContract.View LoginContractView) {
        this.LoginContractView = LoginContractView;
    }

    @Provides
    @NonNull
    LoginContract.View provideLoginContractView() {
        return this.LoginContractView;
    }
}

