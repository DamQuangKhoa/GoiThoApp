package com.demo.architect.data.repository.order.remote;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.ListBookingEntity;
import com.demo.architect.data.model.SaleEntity;

import rx.Observable;

/**
 * Created by Skull on 04/01/2018.
 */

public interface OrderRepository {
    Observable<BaseResponse<ListBookingEntity>> setCalendar(String userId, String contentFix,
                                                            String dateFix, String saleId, String addressFix,
                                                            String phoneFix, String nameFix);

    Observable<BaseResponse<SaleEntity>> checkSaleId(String saleId);
}
