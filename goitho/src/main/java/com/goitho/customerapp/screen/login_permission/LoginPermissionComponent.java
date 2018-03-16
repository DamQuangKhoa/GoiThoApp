package com.goitho.customerapp.screen.login_permission;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {LoginPermissionModule.class})
public interface LoginPermissionComponent {
    void inject(LoginPermissionActivity activity);

}