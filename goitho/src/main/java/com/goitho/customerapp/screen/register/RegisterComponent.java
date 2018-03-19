package com.goitho.customerapp.screen.register;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {RegisterModule.class})
public interface RegisterComponent {
    void inject(RegisterActivity activity);

}
