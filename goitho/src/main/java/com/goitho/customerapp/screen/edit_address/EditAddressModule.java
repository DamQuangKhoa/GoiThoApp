package com.goitho.customerapp.screen.edit_address;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 29/11/2017.
 */

@Module
public class EditAddressModule {
    private final EditAddressContract.View EditAddressContractView;

    public EditAddressModule(EditAddressContract.View EditAddressContractView) {
        this.EditAddressContractView = EditAddressContractView;
    }

    @Provides
    @NonNull
    EditAddressContract.View provideEditAddressContractView() {
        return this.EditAddressContractView;
    }
}

