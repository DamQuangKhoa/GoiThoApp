package com.goitho.customerapp.screen.phone_verification;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class PhoneVerificationModule {
    private final PhoneVerificationContract.View PhoneVerificationContractView;

    public PhoneVerificationModule(PhoneVerificationContract.View PhoneVerificationContractView) {
        this.PhoneVerificationContractView = PhoneVerificationContractView;
    }

    @Provides
    @NonNull
    PhoneVerificationContract.View providePhoneVerificationContractView() {
        return this.PhoneVerificationContractView;
    }
}

