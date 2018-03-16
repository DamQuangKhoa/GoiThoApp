package com.goitho.customerapp.screen.detail_diary;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 29/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {DetailDiaryModule.class})
public interface DetailDiaryComponent {
    void inject(DetailDiaryActivity activity);

}