package com.goitho.customerapp.screen.certificate;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 30/11/2017.
 */

@Module
public class CertificateModule {
    private final CertificateContract.View CertificateContractView;

    public CertificateModule(CertificateContract.View CertificateContractView) {
        this.CertificateContractView = CertificateContractView;
    }

    @Provides
    @NonNull
    CertificateContract.View provideCertificateContractView() {
        return this.CertificateContractView;
    }
}

