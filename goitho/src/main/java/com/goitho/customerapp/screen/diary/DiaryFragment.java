package com.goitho.customerapp.screen.diary;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.model.offline.DiaryEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.DiaryAdapter;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.manager.UserManager;
import com.goitho.customerapp.screen.certificate.CertificateActivity;
import com.goitho.customerapp.screen.detail_diary.DetailDiaryActivity;
import com.goitho.customerapp.screen.edit_detail_diary.EditDetailDiaryActivity;
import com.goitho.customerapp.screen.edit_profile.EditProfileActivity;
import com.goitho.customerapp.screen.farmer.FarmerActivity;
import com.goitho.customerapp.util.Precondition;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Skull on 27/11/2017.
 */

public class DiaryFragment extends BaseFragment implements DiaryContract.View {

    private DiaryContract.Presenter mPresenter;

    @Bind(R.id.rv_diary)
    RecyclerView rvDiary;

    @Bind(R.id.spinner)
    Spinner spinner;

    @Bind(R.id.et_search)
    EditText etSearch;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.nav_user)
    ImageView navUser;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private DiaryAdapter adapter;
    private List<DiaryEntity> list;

    public DiaryFragment() {
        // Required empty public constructor
    }


    public static DiaryFragment newInstance() {
        DiaryFragment fragment = new DiaryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary, container, false);
        ButterKnife.bind(this, view);

        configSpinner();
        initRecyclerView();
        configEdittext();
        configNavigationViewLeft();
        getIntent();
        return view;
    }

    private void configEdittext() {
        Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateEdittext(myCalendar);
            }

        };

        etSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getX() <= (etSearch.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width()))
                    {
                        // your action here
                        new DatePickerDialog(getActivity(), date, myCalendar
                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                        return true;
                    }
                }
                return false;
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                switch (spinner.getSelectedItemPosition()) {
                    case 0:
                        mPresenter.loadListDiary(DiaryPresenter.Filter.BY_DATE, query.toString().toLowerCase());
                        break;
                    case 1:
                        mPresenter.loadListDiary(DiaryPresenter.Filter.BY_WEEK, query.toString().toLowerCase());
                        break;
                    case 2:
                        mPresenter.loadListDiary(DiaryPresenter.Filter.BY_MONTH, query.toString().toLowerCase());
                        break;
                    case 3:
                        mPresenter.loadListDiary(DiaryPresenter.Filter.BY_PRODUCT, query.toString().toLowerCase());
                }
            }
        });
    }

    public static Date getZeroTimeDate(Date fecha) {
        Date res = fecha;
        Calendar calendar = Calendar.getInstance();

        calendar.setTime( fecha );
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        res = calendar.getTime();

        return res;
    }

    private void configSpinner() {
        String[] arrayString = getResources().getStringArray(R.array.array_spinner_search);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayString);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                etSearch.setText(etSearch.getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });etSearch.setText(etSearch.getText());
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter= new DiaryAdapter(new ArrayList<>(), getActivity(),
                new DiaryAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(DiaryEntity item, int position) {
                        DetailDiaryActivity.start(getActivity(),  item);
                    }
                });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvDiary.setLayoutManager(layoutManager);
        rvDiary.setItemAnimator(new DefaultItemAnimator());
        rvDiary.setAdapter(adapter);
    }

    private void updateEdittext(Calendar myCalendar) {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        etSearch.setText(sdf.format(myCalendar.getTime()));

    }

    private void configNavigationViewLeft() {
        navUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);
                }
                else {
                    drawerLayout.openDrawer(Gravity.START);
                }
            }
        });

        View header = navigationView.getHeaderView(0);
        TextView txtName = (TextView) header.findViewById(R.id.txt_name);
        TextView txtAddress = (TextView) header.findViewById(R.id.txt_address);
        LinearLayout llCertificate = (LinearLayout) header.findViewById(R.id.ll_certificate);
        LinearLayout llProfile = (LinearLayout) header.findViewById(R.id.ll_profile);
        LinearLayout llFarmer = (LinearLayout) header.findViewById(R.id.ll_farmer);
        CircleImageView imgUser = (CircleImageView) header.findViewById(R.id.img_user);
        ImageView imgClose = (ImageView) header.findViewById(R.id.img_close);

        if(UserManager.getInstance().getEmployeeUser() != null) {
            llCertificate.setVisibility(View.GONE);
        } else {
            llFarmer.setVisibility(View.GONE);
        }

        llProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditProfileActivity.start(getActivity());
            }
        });

        llCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CertificateActivity.start(getActivity());
            }
        });

        llFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FarmerActivity.start(getActivity());
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);
                }
                else {
                    drawerLayout.openDrawer(Gravity.START);
                }
            }
        });
    }

    private void getIntent(){
        if(getActivity().getIntent() != null) {
            mPresenter.loadActivityList(getActivity().getIntent().getStringExtra(Constants.KEY_FARMER_ID));
        }
    }

    @Override
    public void setPresenter(DiaryContract.Presenter presenter) {
        this.mPresenter = Precondition.checkNotNull(presenter);
    }

    @Override
    public void showProgressBar() {
        showProgressDialog();
    }

    @Override
    public void hideProgressBar() {
        hideProgressDialog();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        spinner.setSelection(0);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.stop();
    }

    @Override
    public void showListDiary(List<DiaryEntity> list) {
        adapter.setData(list);
        this.list=list;
    }

    @Override
    public void showError() {
        Activity activity = getActivity();
        if (activity != null) {
            new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(getString(R.string.text_sweet_dialog_title))
                    .setContentText(getString(R.string.text_sweet_dialog_error))
                    .setConfirmText(getString(R.string.text_sweet_dialog_confirm_text))
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                        }
                    })
                    .show();
        }
    }

    @Override
    public void showDataToNavigation(String name, String address, String avatar, Double rating) {
        View header = navigationView.getHeaderView(0);
        TextView txtName = (TextView) header.findViewById(R.id.txt_name);
        TextView txtAddress = (TextView) header.findViewById(R.id.txt_address);
        CircleImageView imgUser = (CircleImageView) header.findViewById(R.id.img_user);
        RatingBar rbRating = (RatingBar) header.findViewById(R.id.rb_rating);

        txtName.setText(name);
        txtAddress.setText(address);
        if(!TextUtils.isEmpty(avatar)){
            Picasso.with(getActivity()).load(avatar).into(imgUser);
        }
        if (rating == null){
            rbRating.setVisibility(View.GONE);
        } else {
            rbRating.setRating( rating.floatValue());
        }
    }

    @Override
    public void showListDiaryByProduct(List<DiaryEntity> listDiary) {
        adapter.setDataByProduct(listDiary);
    }

    @OnClick(R.id.ll_logout)
    public void logoutLayout(){
        SharedPreferenceHelper.getInstance(getActivity()).pushString(Constants.KEY_TOKEN,null);
        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                .pushFarmerObject(null);
        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                .pushEmployeeObject(null);
        getActivity().finish();
    }

    @OnClick(R.id.btn_logout)
    public void logoutButton(){
        SharedPreferenceHelper.getInstance(getActivity()).pushString(Constants.KEY_TOKEN,null);
        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                .pushFarmerObject(null);
        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                .pushEmployeeObject(null);
        getActivity().finish();
    }

    @OnClick(R.id.txt_add)
    public void addDiary(){
        EditDetailDiaryActivity.start(getActivity());
    }
}
