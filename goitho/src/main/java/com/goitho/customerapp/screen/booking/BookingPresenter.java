package com.goitho.customerapp.screen.booking;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.BookingUsecase;
import com.demo.architect.domain.usecase.CheckSaleUsecase;
import com.demo.architect.domain.usecase.UploadImageSetCalenderUsecase;
import com.demo.architect.utils.view.ImageUtil;
import com.goitho.customerapp.manager.UserManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Skull on 27/11/2017.
 */

public class BookingPresenter implements BookingContract.Presenter {

    private final String TAG = BookingPresenter.class.getName();
    private final BookingContract.View view;
    private final CheckSaleUsecase checkSaleUsecase;
    private final BookingUsecase bookingUsecase;
    private final UploadImageSetCalenderUsecase uploadImageSetCalenderUsecase;
    @Inject
    LocalRepository localRepository;

    @Inject
    BookingPresenter(@NonNull BookingContract.View view, CheckSaleUsecase checkSaleUsecase,
                     BookingUsecase bookingUsecase,
                     UploadImageSetCalenderUsecase uploadImageSetCalenderUsecase) {
        this.view = view;
        this.checkSaleUsecase = checkSaleUsecase;
        this.bookingUsecase = bookingUsecase;
        this.uploadImageSetCalenderUsecase = uploadImageSetCalenderUsecase;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }


    @Override
    public void checkSaleId(String saleId) {
        view.showProgressBar();
        checkSaleUsecase.executeIO(new CheckSaleUsecase.RequestValue(saleId.trim()),
                new BaseUseCase.UseCaseCallback<CheckSaleUsecase.ResponseValue, CheckSaleUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(CheckSaleUsecase.ResponseValue successResponse) {
                        view.showSaleId(saleId);
                        view.hideProgressBar();
                    }

                    @Override
                    public void onError(CheckSaleUsecase.ErrorValue errorResponse) {

                    }
                });
    }

    @Override
    public void booking(String contentFix, String dateFix, String timeFix, String saleId,
                        String addressFix, String phoneFix, String nameFix, List<Bitmap> listAddedBitmaps) {

        String userId = UserManager.getInstance().getUser() != null ? UserManager.getInstance().getUser().getUserId() : "";
        view.showProgressBar();
        bookingUsecase.executeIO(new BookingUsecase.RequestValue(userId,
                        contentFix, dateFix, saleId, addressFix, phoneFix, nameFix),
                new BaseUseCase.UseCaseCallback<BookingUsecase.ResponseValue, BookingUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(BookingUsecase.ResponseValue successResponse) {
                        addImageOrder(successResponse.getEntity().getListOrder().get(0).getOrderId(),
                                listAddedBitmaps);
                    }

                    @Override
                    public void onError(BookingUsecase.ErrorValue errorResponse) {

                    }
                });
    }

    public void addImageOrder(String orderId, List<Bitmap> listAddedBitmaps) {
        List<String> listImagesBase64 = new ArrayList<>();
        for (Bitmap bitmap : listAddedBitmaps) {
            listImagesBase64.add(ImageUtil.encodeImageBase64(bitmap));
        }
        //Step 1: Add images to order entity
        view.showProgressBar();
        for (String imageBase64 : listImagesBase64) {
            uploadImageSetCalenderUsecase.executeIO(new UploadImageSetCalenderUsecase.RequestValue(
                    (UserManager.getInstance().getUser().getUserId()), orderId, imageBase64), new BaseUseCase.UseCaseCallback
                    <UploadImageSetCalenderUsecase.ResponseValue, UploadImageSetCalenderUsecase.ErrorValue>() {
                @Override
                public void onSuccess(UploadImageSetCalenderUsecase.ResponseValue successResponse) {
                    view.hideProgressBar();
                    view.startBookingSuccess();
                }

                @Override
                public void onError(UploadImageSetCalenderUsecase.ErrorValue errorResponse) {

                }
            });
        }
    }

}
