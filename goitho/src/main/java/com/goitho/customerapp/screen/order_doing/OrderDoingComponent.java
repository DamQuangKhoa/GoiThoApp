package com.goitho.customerapp.screen.order_doing;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 27/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {OrderDoingModule.class})
public interface OrderDoingComponent {
    void inject(OrderDoingFragment fragment);

}
