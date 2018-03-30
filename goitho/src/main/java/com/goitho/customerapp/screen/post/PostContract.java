package com.goitho.customerapp.screen.post;


import com.demo.architect.data.model.PostEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.List;

/**
 * Created by MSI on 26/11/2017.
 */

public interface PostContract {
    interface View extends BaseView<Presenter> {
        void showContent();
    }

    interface Presenter extends BasePresenter {
    }
}
