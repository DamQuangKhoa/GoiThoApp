package com.goitho.customerapp.screen.order;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.demo.architect.data.helper.DateHelper;
import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.model.ActivityEntity;
import com.demo.architect.data.model.EmployeeEntity;
import com.demo.architect.data.model.FarmerEntity;
import com.demo.architect.data.model.offline.DiaryEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.GetListActionProductUsecase;
import com.demo.architect.domain.usecase.GetListActivityUsecase;
import com.demo.architect.domain.usecase.GetProductUsecase;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.manager.ActionManager;
import com.goitho.customerapp.manager.ProductManager;
import com.goitho.customerapp.manager.UserManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

/**
 * Created by Skull on 27/11/2017.
 */

public class OrderPresenter implements OrderContract.Presenter{

    private final String TAG = OrderPresenter.class.getName();
    private final OrderContract.View view;
    private GetListActivityUsecase getListActivityUsecase;
    private GetProductUsecase getProductUsecase;
    private GetListActionProductUsecase getListActionProductUsecase;

    List<ActivityEntity> listActivity = new ArrayList<>();
    private String farmerId;
    @Inject
    LocalRepository localRepository;

    @Inject
    OrderPresenter(@NonNull OrderContract.View view, GetListActivityUsecase getListActivityUsecase,
                   GetProductUsecase getProductUsecase,
                   GetListActionProductUsecase getListActionProductUsecase) {
        this.view = view;
        this.getListActivityUsecase = getListActivityUsecase;
        this.getProductUsecase = getProductUsecase;
        this.getListActionProductUsecase = getListActionProductUsecase;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        loadDataOfNavigation();
        if (!TextUtils.isEmpty(farmerId)) {
            loadActivityList(farmerId);
        }
        loadListActionAndProduct();
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }

    @Override
    public void loadListDiary( Filter filter, String query) {
        List<DiaryEntity> listDiary = new ArrayList<>();
        List<Date> listDate = new ArrayList<>();
        if(listActivity.size() >0) {
            listDate.add(listActivity.get(0).getDate());
        }
        for (ActivityEntity activityEntity : listActivity) {
//            if (!listDate.contains(DateHelper.getZeroTimeDate(activityEntity.getDate()))) {
//                listDate.add(activityEntity.getDate());
//            }
            boolean isAdding = true;
            for (Date date : listDate) {
                if (DateHelper.getZeroTimeDate(date).compareTo(DateHelper.getZeroTimeDate(activityEntity.getDate()))==0) {
                    isAdding = false;
                }
            }
            if (isAdding) {
                listDate.add(activityEntity.getDate());
            }
        }

        Collections.sort(listDate, Date::compareTo);

        Date queryDate = null;
        if (!TextUtils.isEmpty(query)) {
            try {
                queryDate = new SimpleDateFormat("dd/MM/yyyy").parse(query.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        switch (filter) {
            case BY_DATE:
                for (Date date : listDate) {
                    ArrayList<ActivityEntity> listActivityOfTheDate = new ArrayList<>();
                    for (ActivityEntity activityEntity : listActivity) {
                        if (DateHelper.getZeroTimeDate(date).compareTo(DateHelper.getZeroTimeDate(
                                activityEntity.getDate())) == 0) {
                            listActivityOfTheDate.add(activityEntity);
                        }
                    }

                    if (listActivityOfTheDate.size() > 0 && (queryDate == null || DateHelper.getZeroTimeDate(date).
                            compareTo(DateHelper.getZeroTimeDate(queryDate)) == 0)) {
                        listDiary.add(new DiaryEntity(UUID.randomUUID().toString(), listActivityOfTheDate));
                    }
                }
                break;
            case BY_WEEK:
                for (Date date : listDate) {
                    ArrayList<ActivityEntity> listActivityOfTheDate = new ArrayList<>();
                    for (ActivityEntity activityEntity : listActivity) {

                        if (DateHelper.getZeroTimeDate(date).compareTo(DateHelper.getZeroTimeDate(
                                activityEntity.getDate())) == 0) {
                            //listActivityOfTheDate.add(activityEntity);

                            Calendar calendar1 = Calendar.getInstance();
                            calendar1.setTime(date);
                            Calendar calendar2 = Calendar.getInstance();
                            calendar2.setTime(activityEntity.getDate());

                            if (calendar1.get(Calendar.WEEK_OF_YEAR) ==
                                    calendar2.get(Calendar.WEEK_OF_YEAR) &&
                                    calendar1.get(Calendar.YEAR) ==
                                            calendar2.get(Calendar.YEAR)) {
                                listActivityOfTheDate.add(activityEntity);
                            }
                        }

                    }

                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(date);
                    Calendar calendar2 = Calendar.getInstance();
                    if(queryDate != null) {
                        calendar2.setTime(queryDate);
                    }
                    if (listActivityOfTheDate.size() > 0 && (queryDate == null || calendar1.get(Calendar.WEEK_OF_YEAR) ==
                            calendar2.get(Calendar.WEEK_OF_YEAR) && calendar1.get(Calendar.YEAR) ==
                            calendar2.get(Calendar.YEAR))) {
                        listDiary.add(new DiaryEntity(UUID.randomUUID().toString(), listActivityOfTheDate));
                    }
                }
                break;
            case BY_MONTH:
                for (Date date : listDate) {
                    ArrayList<ActivityEntity> listActivityOfTheDate = new ArrayList<>();
                    for (ActivityEntity activityEntity : listActivity) {

                        if (DateHelper.getZeroTimeDate(date).compareTo(DateHelper.getZeroTimeDate(
                                activityEntity.getDate())) == 0) {
                            //listActivityOfTheDate.add(activityEntity);

                            Calendar calendar1 = Calendar.getInstance();
                            calendar1.setTime(date);
                            Calendar calendar2 = Calendar.getInstance();
                            calendar2.setTime(activityEntity.getDate());

                            if (calendar1.get(Calendar.MONTH) ==
                                    calendar2.get(Calendar.MONTH) &&
                                    calendar1.get(Calendar.YEAR) ==
                                            calendar2.get(Calendar.YEAR)) {
                                listActivityOfTheDate.add(activityEntity);
                            }
                        }


                    }

                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(date);
                    Calendar calendar2 = Calendar.getInstance();
                    if(queryDate != null) {
                        calendar2.setTime(queryDate);
                    }
                    if (listActivityOfTheDate.size() > 0 && (queryDate == null || calendar1.get(Calendar.MONTH) ==
                            calendar2.get(Calendar.MONTH) && calendar1.get(Calendar.YEAR) ==
                            calendar2.get(Calendar.YEAR))) {
                        listDiary.add(new DiaryEntity(UUID.randomUUID().toString(), listActivityOfTheDate));
                    }
                }
                break;
            case BY_PRODUCT:
                for ( String product : ProductManager.getInstance().getStringListProduct()) {
                    String[] strings = product.split(" - ");
                    String name = strings[0];
                    String id = strings[1];
                    for (Date date : listDate) {
                        ArrayList<ActivityEntity> listActivityOfTheDate = new ArrayList<>();
                        for (ActivityEntity activityEntity : listActivity) {
                            if (DateHelper.getZeroTimeDate(date).compareTo(DateHelper.getZeroTimeDate(
                                    activityEntity.getDate())) == 0) {
                                if (name.toLowerCase().contains(query) &&
                                        activityEntity.getProductId().equals(id))
                                    listActivityOfTheDate.add(activityEntity);
                            }
                        }

                        if (listActivityOfTheDate.size() > 0 ) {
                            listDiary.add(new DiaryEntity(UUID.randomUUID().toString(), listActivityOfTheDate));
                        }
                    }
                }
                view.showListDiaryByProduct(listDiary);
                return;
        }

        view.showListDiary(listDiary);
    }

    @Override
    public void loadDataOfNavigation() {
        if(UserManager.getInstance().getEmployeeUser() !=null){
            EmployeeEntity user = UserManager.getInstance().getEmployeeUser();
            view.showDataToNavigation(user.getName(), "", null, null);
        }else{
            FarmerEntity user = UserManager.getInstance().getFarmerUser();
            view.showDataToNavigation(user.getName(), user.getAddress(), user.getAvatar(), user.getRating());
        }
    }

    @Override
    public void loadActivityList(String farmerId) {
        view.showProgressBar();
        this.farmerId = farmerId;
        getListActivityUsecase.executeIO(new GetListActivityUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).getString(Constants.KEY_TOKEN,""),
                        farmerId),
                new BaseUseCase.UseCaseCallback<GetListActivityUsecase.ResponseValue, GetListActivityUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(GetListActivityUsecase.ResponseValue successResponse) {
                        listActivity= successResponse.getActivityEntities();
                        loadListDiary(Filter.BY_DATE, "");
                        UserManager.getInstance().setSelectedFarmerId(farmerId);
                        view.hideProgressBar();
                    }

                    @Override
                    public void onError(GetListActivityUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }

    void loadListActionAndProduct() {
        getProductUsecase.executeIO(new GetProductUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).getString(Constants.KEY_TOKEN,"")
                ),
                new BaseUseCase.UseCaseCallback<GetProductUsecase.ResponseValue, GetProductUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(GetProductUsecase.ResponseValue successResponse) {
                        ProductManager.getInstance().setList(successResponse.getProductEntity());
                    }

                    @Override
                    public void onError(GetProductUsecase.ErrorValue errorResponse) {
                        //view.showError();
                    }
                });
        getListActionProductUsecase.executeIO(new GetListActionProductUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).getString(Constants.KEY_TOKEN,"")
                ),
                new BaseUseCase.UseCaseCallback<GetListActionProductUsecase.ResponseValue, GetListActionProductUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(GetListActionProductUsecase.ResponseValue successResponse) {
                        ActionManager.getInstance().setListAction(successResponse.getListActionProduct());
                        view.hideProgressBar();
                    }

                    @Override
                    public void onError(GetListActionProductUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        //view.showError();
                    }
                });
    }

    public enum Filter {
        BY_DATE, BY_WEEK, BY_MONTH, BY_PRODUCT
    }
}
