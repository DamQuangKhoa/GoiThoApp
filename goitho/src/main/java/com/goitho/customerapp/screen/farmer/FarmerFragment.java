package com.goitho.customerapp.screen.farmer;

import android.app.Activity;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.model.FarmerEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.FarmerAdapter;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.screen.certificate.CertificateActivity;
import com.goitho.customerapp.screen.diary.DiaryActivity;
import com.goitho.customerapp.screen.edit_profile.EditProfileActivity;
import com.goitho.customerapp.util.Precondition;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Skull on 27/11/2017.
 */

public class FarmerFragment extends BaseFragment implements FarmerContract.View {

    private FarmerContract.Presenter mPresenter;
    
    @Bind(R.id.nav_user)
    ImageView navUser;
    
    @Bind(R.id.rv_farmer)
    RecyclerView rvFarmer;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.btn_logout)
    Button btnLogout;

    @Bind(R.id.et_search)
    EditText etSearch;

    List<FarmerEntity> listFarmer = new ArrayList<>();

    private FarmerAdapter adapter;

    public FarmerFragment() {
        // Required empty public constructor
    }


    public static FarmerFragment newInstance() {
        FarmerFragment fragment = new FarmerFragment();
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
        View view = inflater.inflate(R.layout.fragment_farmer, container, false);
        ButterKnife.bind(this, view);
        
        initRecyclerView();
        configNavigationViewLeft();
        configSearch();
        return view;
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
        CircleImageView imgUser = (CircleImageView) header.findViewById(R.id.img_user);
        LinearLayout llCertificate = (LinearLayout) header.findViewById(R.id.ll_certificate);
        LinearLayout llProfile = (LinearLayout) header.findViewById(R.id.ll_profile);
        LinearLayout llFarmer = (LinearLayout) header.findViewById(R.id.ll_farmer);
        ImageView imgClose = (ImageView) header.findViewById(R.id.img_close);

        llFarmer.setVisibility(View.GONE);

        llProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditProfileActivity.start(getActivity(), true);
            }
        });

        llCertificate.setVisibility(View.GONE);
        llCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CertificateActivity.start(getActivity());
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

    private void initRecyclerView() {
        adapter= new FarmerAdapter(new ArrayList<FarmerEntity>(), getActivity(),
                new FarmerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(FarmerEntity item) {
                        startDiaryActivity(item.getId());
                    }
                });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvFarmer.setLayoutManager(layoutManager);
        rvFarmer.setItemAnimator(new DefaultItemAnimator());
        rvFarmer.setAdapter(adapter);
    }

    private void configSearch(){
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    void filter(String text){
        List<FarmerEntity> temp = new ArrayList();
        for(FarmerEntity d: listFarmer){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d.getName().toLowerCase().contains(text.toLowerCase())){
                temp.add(d);
            }
        }
        //update recyclerview
        //if(temp.size()>0)
        adapter.setData(temp);
        //else
        //    adapter.setData(listFarmer);
    }

    @Override
    public void setPresenter(FarmerContract.Presenter presenter) {
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
        if(TextUtils.isEmpty(SharedPreferenceHelper.getInstance(getActivity()).getString(Constants.KEY_TOKEN,"")))
            getActivity().finish();
        mPresenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.stop();
    }

    @Override
    public void showListFarmer(List<FarmerEntity> list) {
        adapter.setData(list);
        listFarmer = list;
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
    public void startDiaryActivity(String farmerId) {
        DiaryActivity.start(getActivity(), farmerId);
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
}
