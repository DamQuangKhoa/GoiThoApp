package com.demo.architect.data.repository.action.product.remote;

import com.demo.architect.data.model.ActionEntity;
import com.demo.architect.data.model.BaseResponse;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Skull on 08/01/2018.
 */

public interface ActionProductRepository {
    Observable<BaseResponse<List<ActionEntity>>> findListActionProduct(String token);
    Observable<BaseResponse<ActionEntity>> createActionProduct(String token, String name);
    Observable<BaseResponse<ActionEntity>> deleteActionProduct(String token, String id);

}
