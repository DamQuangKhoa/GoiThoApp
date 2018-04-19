package com.goitho.customerapp.screen.order;

import com.demo.architect.data.model.OrderEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.List;

/**
 * Created by Skull on 27/11/2017.
 */

public interface OrderContract {
    interface View extends BaseView<Presenter> {
        void showOrderDoingList(List<OrderEntity> list);
        void showOrderDoneList(List<OrderEntity> list);
    }

    interface Presenter extends BasePresenter {
        List<OrderEntity> orderDoneList();

        List<OrderEntity> orderDoingList();
    }
}

