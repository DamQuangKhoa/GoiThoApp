package com.goitho.customerapp.screen.diary;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 27/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {DiaryModule.class})
public interface DiaryComponent {
    void inject(DiaryActivity activity);

}
