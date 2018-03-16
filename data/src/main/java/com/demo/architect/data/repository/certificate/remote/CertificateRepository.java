package com.demo.architect.data.repository.certificate.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.CertificateEntity;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Skull on 14/01/2018.
 */

public interface CertificateRepository {
    Observable<BaseResponse<List<CertificateEntity>>> findListCertificateByFarmerId(String token, String filter);
    Observable<BaseResponse<CertificateEntity>> createCertificate(String token, HashMap<String, String> fields);
    Observable<BaseResponse<CertificateEntity>> updateCertificate(String token, String id, HashMap<String, String> fields);
}
