package com.goitho.customerapp.screen.promotion;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {PromotionModule.class})
public interface PromotionComponent {
    void inject(PromotionActivity activity);

}