package com.goitho.customerapp.screen.booking;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 27/11/2017.
 */


@Module
public class BookingModule {
    private final BookingContract.View BookingContractView;

    public BookingModule(BookingContract.View BookingContractView) {
        this.BookingContractView = BookingContractView;
    }

    @Provides
    @NonNull
    BookingContract.View provideBookingContractView() {
        return this.BookingContractView;
    }
}

