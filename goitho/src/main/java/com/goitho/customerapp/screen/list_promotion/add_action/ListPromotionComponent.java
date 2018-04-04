package com.goitho.customerapp.screen.list_promotion.add_action;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 11/12/2017.
 */

@ActivityScope
@Subcomponent(modules = {ListPromotionModule.class})
public interface ListPromotionComponent {
    void inject(ListPromotionActivity activity);

}