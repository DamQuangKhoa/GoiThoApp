package com.goitho.customerapp.screen.edit_profile;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.manager.UserManager;
import com.goitho.customerapp.util.Precondition;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.goitho.customerapp.screen.certificate.CertificateFragment.REQUEST_CODE_PICK_IMAGE;
import static com.goitho.customerapp.screen.certificate.CertificateFragment.REQUEST_CODE_TAKE_IMAGE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Skull on 29/11/2017.
 */

public class EditProfileFragment extends BaseFragment implements EditProfileContract.View {

    private EditProfileContract.Presenter mPresenter;


    @Bind(R.id.txt_back)
    TextView txtBack;

    @Bind(R.id.et_username)
    EditText etUsername;

    @Bind(R.id.et_password)
    EditText etPassword;

    @Bind(R.id.et_phone)
    EditText etPhone;

    @Bind(R.id.img_logo)
    ImageView imgLogo;

    @Bind(R.id.layout)
    RelativeLayout layout;


    public EditProfileFragment() {
        // Required empty public constructor
    }


    public static EditProfileFragment newInstance() {
        EditProfileFragment fragment = new EditProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        ButterKnife.bind(this, view);
        setBackground();
        return view;
    }

    private void setBackground() {
        RelativeLayout.LayoutParams relativeParams = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        int heightLogo = imgLogo.getDrawable().getIntrinsicHeight();
        relativeParams.setMargins(0, (heightLogo / 2), 0, 0);  // left, top, right, bottom
        layout.setLayoutParams(relativeParams);
        txtBack.setPaintFlags(txtBack.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }


    @OnClick(R.id.txt_back)
    public void back() {
        getActivity().finish();
    }

    @OnClick(R.id.btn_update)
    public void updateProfile() {

    }



    @Override
    public void setPresenter(EditProfileContract.Presenter presenter) {
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
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.stop();
    }

    @Override
    public void showError() {

    }

}
