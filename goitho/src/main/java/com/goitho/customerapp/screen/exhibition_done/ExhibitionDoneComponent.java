package com.goitho.customerapp.screen.exhibition_done;

import com.goitho.customerapp.app.di.ActivityScope;
import com.goitho.customerapp.screen.exhibition.ExhibitionActivity;

import dagger.Subcomponent;

/**
 * Created by Skull on 27/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {ExhibitionDoneModule.class})
public interface ExhibitionDoneComponent {
    void inject(ExhibitionDoneFragment fragment);

}
