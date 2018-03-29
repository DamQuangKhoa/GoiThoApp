package com.goitho.customerapp.screen.home;

import com.demo.architect.data.model.BlogEntity;
import com.demo.architect.data.model.RatingEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MSI on 26/11/2017.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void showRatingList(ArrayList<RatingEntity> list);
        void showBlogList(List<BlogEntity> list);
        void startDetailOrder();
    }

    interface Presenter extends BasePresenter {
        ArrayList<RatingEntity> ratingList();
        List<BlogEntity> blogList();
    }
}
