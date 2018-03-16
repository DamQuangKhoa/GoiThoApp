package com.goitho.customerapp.screen.certificate;

import com.demo.architect.data.model.CertificateEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skull on 30/11/2017.
 */

public interface CertificateContract {
    interface View extends BaseView<Presenter> {
        void showError();
        void showCertificates(List<CertificateEntity> list);

        void finishActivity();
    }

    interface Presenter extends BasePresenter {
        void loadCertiicates();

        void createCertificate(CertificateEntity entity);

        void editCertificate(CertificateEntity entity);

        void uploadImage(CertificateEntity entity, ArrayList<File> listFile, boolean isCreating);
    }
}
