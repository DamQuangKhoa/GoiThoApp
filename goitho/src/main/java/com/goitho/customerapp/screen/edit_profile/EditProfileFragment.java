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

public class EditProfileFragment extends BaseFragment implements EditProfileContract.View,
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener,
        LocationListener {

    private EditProfileContract.Presenter mPresenter;

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;
    private Marker currentLocationMarker;
    private Marker clickMarker;
    public static final String MAP_FRAGMENT_TAG = "mapFragment";

    private static final int GOOGLE_API_CLIENT_ID = 0;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    public static final long INTERVAL_TIME_MILISECOND = 1000;

    @Bind(R.id.img_back)
    ImageView imgBack;

    @Bind(R.id.img_user)
    ImageView imgUser;

    @Bind(R.id.txt_name)
    TextView txtName;

    @Bind(R.id.et_name)
    EditText etName;

    @Bind(R.id.ln_address)
    LinearLayout lnAddress;

    @Bind(R.id.txt_address)
    TextView txtAddress;

    @Bind(R.id.txt_address_top)
    TextView getTxtAddressTop;

    @Bind(R.id.et_address)
    EditText etAddress;

    @Bind(R.id.rb_rate)
    RatingBar rbRate;

    @Bind(R.id.txt_id)
    TextView txtId;

    @Bind(R.id.tv_id_text)
    TextView txtIdText;

    @Bind(R.id.mapFragmentContainer)
    RelativeLayout mapFragmentContainer;

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

        initMapFragment();
        connectAPIClient();
        configEditText();
        return view;
    }

    private void initMapFragment() {
        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentByTag(MAP_FRAGMENT_TAG);
        if (mapFragment == null) {
            mapFragment = new SupportMapFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.mapFragmentContainer, mapFragment, MAP_FRAGMENT_TAG);
            ft.commit();
            fm.executePendingTransactions();
        }
        mapFragment.getMapAsync(this);
    }

    private void connectAPIClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .enableAutoManage(getActivity(), GOOGLE_API_CLIENT_ID, this)
                    .build();
            mGoogleApiClient.connect();
        }
    }


    private void setUpMap() {
        if (ActivityCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]
                    {android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }
        mMap.setMyLocationEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.setOnMarkerClickListener(this);

//        LocationAvailability locationAvailability =
//                LocationServices.FusedLocationApi.getLocationAvailability(mGoogleApiClient);
//        if (null != locationAvailability && locationAvailability.isLocationAvailable()) {
//            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//            if (mLastLocation != null) {
//                LatLng currentLocation = new LatLng(mLastLocation.getLatitude(), mLastLocation
//                        .getLongitude());
//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
//
//                //setCurentLocationMarker(mLastLocation);
//            }
//        }
    }

    private void moveToCurrentLocation() {
        if (mLastLocation != null) {
            LatLng currentLocation = new LatLng(mLastLocation.getLatitude(), mLastLocation
                    .getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
        }
    }

//    private void setCurentLocationMarker(Location location) {
//        if(currentLocationMarker != null){
//            currentLocationMarker.remove();
//        }
//        currentLocationMarker = mMap.addMarker(new MarkerOptions()
//                .flat(true)
//                .icon(BitmapDescriptorFactory
//                        .fromBitmap(smallMarker))
//                .anchor(0.5f, 0.5f)
//                .position(
//                        new LatLng(location.getLatitude(), location
//                                .getLongitude())));
//    }

    public void configEditText() {
        etName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (etName.getRight() -
                            etName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        if (!TextUtils.isEmpty(etName.getText())) {
                            etName.setVisibility(View.GONE);
                            txtName.setVisibility(View.VISIBLE);
                            txtName.setText(etName.getText());
                        }
                        return true;
                    }
                }
                return false;
            }
        });

        etAddress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (etAddress.getRight() -
                            etAddress.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        if (!TextUtils.isEmpty(etAddress.getText())) {
                            etAddress.setVisibility(View.GONE);
                            txtAddress.setVisibility(View.VISIBLE);
                            txtAddress.setText(etAddress.getText());
                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.text_save_address));
        builder.setCancelable(false);
        builder.setPositiveButton(getString(R.string.text_agree), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(getString(R.string.text_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.show();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setScrollGesturesEnabled(false);
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.clear();
                MarkerOptions options = new MarkerOptions();
                options.position(latLng);
                options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                mMap.addMarker(options);
                showAlertDialog();
            }
        });
        mPresenter.loadMapData();
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = new Location(location);
        //setCurentLocationMarker(location);
    }

    @Override
    public void onConnected(Bundle bundle) {
        setUpMap();
        //update current location
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL_TIME_MILISECOND * 35);
        mLocationRequest.setFastestInterval(1000 * 35);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest,
                    this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @OnClick(R.id.img_back)
    public void back() {
        getActivity().finish();
    }

    @OnClick(R.id.txt_name)
    public void changeName() {
        txtName.setVisibility(View.GONE);
        etName.setVisibility(View.VISIBLE);
        etName.setText(txtName.getText());
    }

    @OnClick(R.id.txt_address)
    public void changeAddress() {
        txtAddress.setVisibility(View.GONE);
        etAddress.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(txtAddress.getText())) {
            etAddress.setText(txtAddress.getText());
        }
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

    @Override
    public void showDataToNavigation(String name, String address, String avatar, Double rating,
                                     String id, boolean isEmployee) {
        txtName.setText(name);

        if (TextUtils.isEmpty(address)) {
            lnAddress.setVisibility(View.GONE);
            getTxtAddressTop.setVisibility(View.GONE);
        } else {
            lnAddress.setVisibility(View.VISIBLE);
            getTxtAddressTop.setVisibility(View.VISIBLE);
        }
        txtAddress.setText(address);
        getTxtAddressTop.setText(address);

        if (rating == null)
            rbRate.setVisibility(View.GONE);
        else
            rbRate.setRating(rating.floatValue());

        txtId.setText(id);

        if (!TextUtils.isEmpty(avatar))
            Picasso.with(getActivity()).load(avatar).into(imgUser);

        txtIdText.setText(getString(isEmployee ? R.string.text_employee_id : R.string.text_farmer_id));
    }

    @Override
    public void showMapData(Double latitude, Double longitude) {
        if (latitude != null && longitude != null) {

            mMap.clear();
            MarkerOptions options = new MarkerOptions();
            options.position(new LatLng(latitude, longitude));
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            mMap.addMarker(options);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));
        } else {
            mapFragmentContainer.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.img_user)
    public void changeImage() {
        if (UserManager.getInstance().getEmployeeUser() != null)
            return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            //your code
            showAlertDialogImage();
        }
    }

    public void showAlertDialogImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.text_add_image));
        builder.setCancelable(false);
        builder.setPositiveButton(getString(R.string.text_pick_picture),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, REQUEST_CODE_PICK_IMAGE);
                        dialogInterface.dismiss();
                    }
                });
        builder.setNegativeButton(getString(R.string.text_take_picture),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, REQUEST_CODE_TAKE_IMAGE);
                        dialog.dismiss();
                    }
                });
        builder.setNeutralButton(getString(R.string.text_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.show();

    }

    File file;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_PICK_IMAGE) {
            try {
                Uri imageUri = data.getData();
                InputStream imageStream = getActivity().getContentResolver()
                        .openInputStream(imageUri);
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                showResultImageDialog(selectedImage);

                file = new File(getRealPathFromURI(imageUri));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_TAKE_IMAGE) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            showResultImageDialog(photo);
            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(getApplicationContext(), photo);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            file = new File(getRealPathFromURI(tempUri));

        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getActivity().getContentResolver()
                .query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media
                .insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void showResultImageDialog(Bitmap bitmap) {
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(getLayoutInflater().inflate(R.layout.image_layout, null));
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        Button btnOk = (Button) dialog.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        image.setImageBitmap(bitmap);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.uploadImage(file);
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
