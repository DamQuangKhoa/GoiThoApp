package com.demo.architect.data.repository.bill.remote;

import com.demo.architect.data.model.IMDBEntity;

import rx.Observable;

/**
 * Created by admin on 7/10/17.
 */

public interface BillRepository {
    Observable<IMDBEntity> getIMDB(String url);
}
