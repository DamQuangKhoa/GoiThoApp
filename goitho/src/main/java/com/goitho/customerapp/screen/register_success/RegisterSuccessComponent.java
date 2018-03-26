package com.goitho.customerapp.screen.register_success;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {RegisterSuccessModule.class})
public interface RegisterSuccessComponent {
    void inject(RegisterSuccessActivity activity);

}
