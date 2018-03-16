package com.demo.architect.data.repository.employee.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.EmployeeEntity;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Skull on 05/01/2018.
 */

public interface EmployeeRepository {
    Observable<BaseResponse<List<EmployeeEntity>>> findListEmployee(String token);
    Observable<BaseResponse<EmployeeEntity>> updateEmployee(String token, String id, HashMap<String, String> fields);

}
