package com.goitho.customerapp.screen.dashboard;

import com.goitho.customerapp.app.di.ActivityScope;
import com.goitho.customerapp.screen.booking.BookingModule;
import com.goitho.customerapp.screen.home.HomeModule;
import com.goitho.customerapp.screen.notification.NotificationModule;
import com.goitho.customerapp.screen.order.OrderModule;
import com.goitho.customerapp.screen.user.UserModule;

import dagger.Subcomponent;

/**
 * Created by Skull on 14/12/2017.
 */

@ActivityScope
@Subcomponent(modules = {HomeModule.class, OrderModule.class, BookingModule.class ,NotificationModule.class, UserModule.class})
public interface DashboardComponent {
    void inject(DashboardFragment fragment);

}
