package com.goitho.customerapp.screen.home;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class HomeModule {
    private final HomeContract.View NotificationContractView;

    public HomeModule(HomeContract.View NotificationContractView) {
        this.NotificationContractView = NotificationContractView;
    }

    @Provides
    @NonNull
    HomeContract.View provideNotificationContractView() {
        return this.NotificationContractView;
    }
}

