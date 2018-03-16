package com.goitho.customerapp.screen.add_action;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 11/12/2017.
 */

@ActivityScope
@Subcomponent(modules = {AddActionModule.class})
public interface AddActionComponent {
    void inject(AddActionActivity activity);

}