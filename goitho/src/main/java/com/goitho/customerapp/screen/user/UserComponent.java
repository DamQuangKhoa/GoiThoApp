package com.goitho.customerapp.screen.user;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {UserModule.class})
public interface UserComponent {
    void inject(UserActivity activity);

}
