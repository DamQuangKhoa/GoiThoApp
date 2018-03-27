package com.goitho.customerapp.screen.home;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {HomeModule.class})
public interface HomeComponent {
    void inject(HomeActivity fragment);

}
