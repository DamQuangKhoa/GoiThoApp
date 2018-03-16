package com.goitho.customerapp.screen.edit_profile;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 29/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {EditProfileModule.class})
public interface EditProfileComponent {
    void inject(EditProfileActivity activity);

}
