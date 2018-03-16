package com.goitho.customerapp.screen.login_permission;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */


@Module
public class LoginPermissionModule {
    private final LoginPermissionContract.View LoginPermissionContractView;

    public LoginPermissionModule(LoginPermissionContract.View LoginPermissionContractView) {
        this.LoginPermissionContractView = LoginPermissionContractView;
    }

    @Provides
    @NonNull
    LoginPermissionContract.View provideLoginPermissionContractView() {
        return this.LoginPermissionContractView;
    }
}

