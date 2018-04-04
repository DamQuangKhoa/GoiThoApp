package com.goitho.customerapp.screen.detail_order;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {DetailOrderModule.class})
public interface DetailOrderComponent {
    void inject(DetailOrderActivity fragment);

}
