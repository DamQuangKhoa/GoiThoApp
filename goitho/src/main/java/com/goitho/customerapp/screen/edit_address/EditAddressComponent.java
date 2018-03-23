package com.goitho.customerapp.screen.edit_address;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 29/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {EditAddressModule.class})
public interface EditAddressComponent {
    void inject(EditAddressActivity activity);

}
