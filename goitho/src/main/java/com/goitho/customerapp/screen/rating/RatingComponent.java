package com.goitho.customerapp.screen.rating;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {RatingModule.class})
public interface RatingComponent {
    void inject(RatingActivity activity);

}
