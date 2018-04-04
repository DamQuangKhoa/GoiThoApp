package com.goitho.customerapp.screen.landing;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {LandingModule.class})
public interface LandingComponent {
    void inject(LandingFragment fragment);

}