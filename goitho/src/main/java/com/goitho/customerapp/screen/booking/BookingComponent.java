package com.goitho.customerapp.screen.booking;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 27/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {BookingModule.class})
public interface BookingComponent {
    void inject(BookingActivity activity);

}
