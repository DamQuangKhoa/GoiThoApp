package com.demo.architect.data.repository.base.remote;


import com.demo.architect.data.model.EmployeeEntity;
import com.demo.architect.data.model.FarmerEntity;
import com.demo.architect.data.model.IMDBEntity;
import com.demo.architect.data.model.MessageModel;

import java.util.List;

import rx.Observable;

/**
 * Created by uyminhduc on 10/16/16.
 */

public interface RemoteRepository {

    Observable<IMDBEntity> getIMDB();

    Observable<String> add(MessageModel model);

    Observable<List<MessageModel>> findAll();

    Observable<FarmerEntity> loginFarmer(String email, String password);

    Observable<EmployeeEntity> loginEmployee(String email, String password);

}
