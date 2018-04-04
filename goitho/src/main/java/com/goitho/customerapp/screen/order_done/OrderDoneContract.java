package com.goitho.customerapp.screen.order_done;

import com.demo.architect.data.model.OrderEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.List;

/**
 * Created by Skull on 27/11/2017.
 */

public interface OrderDoneContract {
    interface View extends BaseView<Presenter> {
        void showOrderDoneList(List<OrderEntity> list);
    }

    interface Presenter extends BasePresenter {
        List<OrderEntity> list();
    }
}

