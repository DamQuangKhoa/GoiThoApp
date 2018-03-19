package com.goitho.customerapp.screen.user;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class UserModule {
    private final UserContract.View PhoneVerificationContractView;

    public UserModule(UserContract.View PhoneVerificationContractView) {
        this.PhoneVerificationContractView = PhoneVerificationContractView;
    }

    @Provides
    @NonNull
    UserContract.View providePhoneVerificationContractView() {
        return this.PhoneVerificationContractView;
    }
}

