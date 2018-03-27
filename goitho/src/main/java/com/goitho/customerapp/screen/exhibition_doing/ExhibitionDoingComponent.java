package com.goitho.customerapp.screen.exhibition_doing;

import com.goitho.customerapp.app.di.ActivityScope;
import com.goitho.customerapp.screen.exhibition.ExhibitionActivity;

import dagger.Subcomponent;

/**
 * Created by Skull on 27/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {ExhibitionDoingModule.class})
public interface ExhibitionDoingComponent {
    void inject(ExhibitionDoingFragment fragment);

}
