package com.goitho.customerapp.screen.farmer;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 27/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {FarmerModule.class})
public interface FarmerComponent {
    void inject(FarmerActivity activity);

}