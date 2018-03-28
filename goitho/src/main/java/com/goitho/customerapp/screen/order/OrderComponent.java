package com.goitho.customerapp.screen.order;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 27/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {OrderModule.class})
public interface OrderComponent {
    void inject(OrderFragment fragment);

}
