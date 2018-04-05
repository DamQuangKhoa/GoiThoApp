package com.goitho.customerapp.screen.detail_promotion;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 11/12/2017.
 */

@ActivityScope
@Subcomponent(modules = {DetailPromotionModule.class})
public interface DetailPromotionComponent {
    void inject(DetailPromotionActivity activity);

}