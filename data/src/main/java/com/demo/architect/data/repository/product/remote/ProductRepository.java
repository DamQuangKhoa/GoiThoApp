package com.demo.architect.data.repository.product.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.ProductEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Skull on 07/01/2018.
 */

public interface ProductRepository {
    Observable<BaseResponse<List<ProductEntity>>> findProduct(String token);
}
