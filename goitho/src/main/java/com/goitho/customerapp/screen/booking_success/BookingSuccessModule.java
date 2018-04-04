package com.goitho.customerapp.screen.booking_success;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class BookingSuccessModule {
    private final BookingSuccessContract.View LoginContractView;

    public BookingSuccessModule(BookingSuccessContract.View LoginContractView) {
        this.LoginContractView = LoginContractView;
    }

    @Provides
    @NonNull
    BookingSuccessContract.View provideLoginContractView() {
        return this.LoginContractView;
    }
}

