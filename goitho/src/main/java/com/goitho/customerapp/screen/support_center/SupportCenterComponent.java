package com.goitho.customerapp.screen.support_center;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {SupportCenterModule.class})
public interface SupportCenterComponent {
    void inject(SupportCenterActivity activity);

}
