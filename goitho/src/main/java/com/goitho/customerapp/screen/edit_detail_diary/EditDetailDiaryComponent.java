package com.goitho.customerapp.screen.edit_detail_diary;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 01/12/2017.
 */

@ActivityScope
@Subcomponent(modules = {EditDetailDiaryModule.class})
public interface EditDetailDiaryComponent {
    void inject(EditDetailDiaryActivity activity);

}
