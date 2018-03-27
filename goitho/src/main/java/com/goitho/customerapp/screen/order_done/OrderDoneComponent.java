package com.goitho.customerapp.screen.order_done;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 27/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {OrderDoneModule.class})
public interface OrderDoneComponent {
    void inject(OrderDoneFragment fragment);

}
