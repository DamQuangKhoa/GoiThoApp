package com.goitho.customerapp.screen.edit_profile;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 29/11/2017.
 */

@Module
public class EditProfileModule {
    private final EditProfileContract.View EditProfileContractView;

    public EditProfileModule(EditProfileContract.View EditProfileContractView) {
        this.EditProfileContractView = EditProfileContractView;
    }

    @Provides
    @NonNull
    EditProfileContract.View provideEditProfileContractView() {
        return this.EditProfileContractView;
    }
}

