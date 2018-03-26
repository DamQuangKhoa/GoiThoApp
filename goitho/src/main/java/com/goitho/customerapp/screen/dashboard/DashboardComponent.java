package com.goitho.customerapp.screen.dashboard;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 14/12/2017.
 */

@ActivityScope
@Subcomponent(modules = {DashboardModule.class})
public interface DashboardComponent {
    void inject(DashboardActivity activity);

}
