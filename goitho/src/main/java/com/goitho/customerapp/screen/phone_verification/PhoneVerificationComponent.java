package com.goitho.customerapp.screen.phone_verification;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {PhoneVerificationModule.class})
public interface PhoneVerificationComponent {
    void inject(PhoneVerificationActivity activity);

}
