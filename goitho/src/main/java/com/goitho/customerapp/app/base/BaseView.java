package com.goitho.customerapp.app.base;

/**
 * Created by uyminhduc on 12/16/16.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
    void showProgressBar();
    void hideProgressBar();
}
