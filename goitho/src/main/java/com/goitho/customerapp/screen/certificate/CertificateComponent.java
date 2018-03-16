package com.goitho.customerapp.screen.certificate;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 30/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {CertificateModule.class})
public interface CertificateComponent {
    void inject(CertificateActivity activity);

}
