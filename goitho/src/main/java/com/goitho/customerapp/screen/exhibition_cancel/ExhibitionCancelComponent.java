package com.goitho.customerapp.screen.exhibition_cancel;

import com.goitho.customerapp.app.di.ActivityScope;
import com.goitho.customerapp.screen.exhibition.ExhibitionActivity;

import dagger.Subcomponent;

/**
 * Created by Skull on 27/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {ExhibitionCancelModule.class})
public interface ExhibitionCancelComponent {
    void inject(ExhibitionCancelFragment fragment);

}
