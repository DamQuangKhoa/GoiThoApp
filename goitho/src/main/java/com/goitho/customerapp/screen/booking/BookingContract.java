package com.goitho.customerapp.screen.booking;

import android.graphics.Bitmap;

import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.List;

/**
 * Created by Skull on 27/11/2017.
 */

public interface BookingContract {
    interface View extends BaseView<Presenter> {
        void startBookingSuccess();
        void startDialogPromotion();
        void showSaleId(String code);
    }

    interface Presenter extends BasePresenter {
        void checkSaleId(String saleId);
        void booking(String contentFix, String dateFix, String timeFix, String saleId, String addressFix,
                     String phoneFix, String nameFix,List<Bitmap> listAddedBitmaps);

    }
}

