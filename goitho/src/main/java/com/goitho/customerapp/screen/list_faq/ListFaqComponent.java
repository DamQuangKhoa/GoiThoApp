package com.goitho.customerapp.screen.list_faq;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 11/12/2017.
 */

@ActivityScope
@Subcomponent(modules = {ListFaqModule.class})
public interface ListFaqComponent {
    void inject(ListFaqActivity activity);

}