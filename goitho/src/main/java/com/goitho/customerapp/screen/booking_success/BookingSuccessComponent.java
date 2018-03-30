package com.goitho.customerapp.screen.booking_success;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {BookingSuccessModule.class})
public interface BookingSuccessComponent {
    void inject(BookingSuccessActivity activity);

}
