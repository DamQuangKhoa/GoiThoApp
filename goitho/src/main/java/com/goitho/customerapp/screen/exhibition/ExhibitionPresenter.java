package com.goitho.customerapp.screen.exhibition;

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

public class ExhibitionPresenter {

}
