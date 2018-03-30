package com.goitho.customerapp.screen.blog;


import com.demo.architect.data.model.PostEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.List;

/**
 * Created by MSI on 26/11/2017.
 */

public interface BlogContract {
    interface View extends BaseView<Presenter> {
        void showBlogList(List<PostEntity> list);
    }

    interface Presenter extends BasePresenter {
        List<PostEntity> blogList();
    }
}
