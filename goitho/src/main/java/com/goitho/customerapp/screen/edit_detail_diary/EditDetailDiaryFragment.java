package com.goitho.customerapp.screen.edit_detail_diary;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.demo.architect.data.model.ActionEntity;
import com.demo.architect.data.model.ActivityBuyFertilizerEntity;
import com.demo.architect.data.model.ActivityEntity;
import com.demo.architect.data.model.ActivityUseFertilizerEntity;
import com.demo.architect.data.model.ProductEntity;
import com.demo.architect.data.model.offline.ImageEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.ActionSpinnerAdapter;
import com.goitho.customerapp.adapter.ImageAdapter;
import com.goitho.customerapp.adapter.ImageGridAdapter;
import com.goitho.customerapp.adapter.ProductSpinnerAdapter;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.manager.ActionManager;
import com.goitho.customerapp.manager.ProductManager;
import com.goitho.customerapp.manager.UserManager;
import com.goitho.customerapp.screen.add_action.AddActionActivity;
import com.goitho.customerapp.screen.delete_action.DeleteActionActivity;
import com.goitho.customerapp.util.Precondition;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationAvailability;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.app.Activity.RESULT_OK;
import static com.goitho.customerapp.screen.certificate.CertificateFragment.REQUEST_CODE_PICK_IMAGE;
import static com.goitho.customerapp.screen.certificate.CertificateFragment.REQUEST_CODE_TAKE_IMAGE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Skull on 01/12/2017.
 */

public class EditDetailDiaryFragment extends BaseFragment implements EditDetailDiaryContract.View,
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener,
        LocationListener {

    private EditDetailDiaryContract.Presenter mPresenter;

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
    private boolean isCreatingNewAcivity = true;

    ArrayList<ImageEntity> listImage;
    ArrayList<ImageEntity> imagesFromGallery = new ArrayList<>();

    ImageAdapter adapter;

    @Bind(R.id.txt_title_toolbar)
    TextView txtTitleToolbar;


    @Bind(R.id.spin_product)
    Spinner spinProduct;

    @Bind(R.id.spin_type_action)
    Spinner spinTypeAction;

    @Bind(R.id.txt_datetime)
    TextView txtDateTime;

    @Bind(R.id.img_calendar)
    ImageView imgCalendar;

    @Bind(R.id.et_note)
    EditText etNote;

    @Bind(R.id.img_add)
    ImageView imgAdd;

    @Bind(R.id.rv_image)
    RecyclerView rvImage;

    @Bind(R.id.grid)
    GridView grid;

    //LAYOUT BUY
    @Bind(R.id.layout_buy)
    LinearLayout llBuy;

    @Bind(R.id.et_fertilizer_buy)
    EditText etFertilizerBuy;

    @Bind(R.id.et_fertilizer_id_buy)
    EditText etFertilizerIdBuy;

    @Bind(R.id.et_quality_buy)
    EditText etQualityBuy;

    @Bind(R.id.et_quantity_buy)
    EditText etQuantityBuy;

    @Bind(R.id.et_company)
    EditText etCompany;

    @Bind(R.id.et_store)
    EditText etStore;

    @Bind(R.id.txt_exp_date_buy)
    TextView txtExpDateBuy;

    @Bind(R.id.img_calendar_buy)
    ImageView getImgCalendarBuy;

    //LAYOUT USE
    @Bind(R.id.layout_use)
    LinearLayout llUse;

    @Bind(R.id.et_field_id)
    EditText etFieldIdUse;

    @Bind(R.id.et_field_area)
    EditText etFieldAreaUse;

    @Bind(R.id.et_fertilizer)
    EditText etFertilizerUse;

    @Bind(R.id.et_fertilizer_id)
    EditText etFertilizerIdUse;

    @Bind(R.id.et_dosage)
    EditText etDosageUse;

    @Bind(R.id.et_quantity)
    EditText etQuantityUse;

    @Bind(R.id.et_stock)
    EditText etStockUse;

    @Bind(R.id.et_goal)
    EditText etGoalUse;

    @Bind(R.id.et_equiment)
    EditText etEquimentUse;

    @Bind(R.id.et_isolation_time)
    EditText etIsolationTimeUse;

    @Bind(R.id.rb_true)
    RadioButton rbTrue;

    @Bind(R.id.rb_false)
    RadioButton rbFalse;

    ActivityEntity item;
    ArrayList<String> listAction = new ArrayList<>();
    ArrayList<String> listProduct = new ArrayList<>();
    ArrayList<Uri> listUri = new ArrayList<>();
    ArrayList<File> listFile = new ArrayList<>();
    ActionSpinnerAdapter actionAdapter;
    ProductSpinnerAdapter productAdapter;
    private ImageGridAdapter gridAdapter;

    public EditDetailDiaryFragment() {
        // Required empty public constructor
    }

    public static EditDetailDiaryFragment newInstance() {
        EditDetailDiaryFragment fragment = new EditDetailDiaryFragment();
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
        View view = inflater.inflate(R.layout.fragment_edit_detail_diary, container, false);
        ButterKnife.bind(this, view);
        initMapFragment();
        connectAPIClient();
        //initRecyclerView();
        configGridView();
        Calendar calendar = Calendar.getInstance();
        updateDateTimeTextView(calendar.getTime());
        updateExpDateBuy(calendar.getTime());

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
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.setOnMarkerClickListener(this);

        LocationAvailability locationAvailability =
                LocationServices.FusedLocationApi.getLocationAvailability(mGoogleApiClient);
        if (null != locationAvailability && locationAvailability.isLocationAvailable()) {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLastLocation != null) {
                LatLng currentLocation = new LatLng(mLastLocation.getLatitude(), mLastLocation
                        .getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));

                //setCurentLocationMarker(mLastLocation);
            }
        }
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

    public void showAlertDialogMap() {
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

    private void initRecyclerView() {
        listImage = new ArrayList<>();
        adapter = new ImageAdapter(new ArrayList<ImageEntity>(), getActivity(),
                new ImageAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(ImageEntity item) {
                    }
                });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        rvImage.setHasFixedSize(true);
        rvImage.setLayoutManager(layoutManager);
        rvImage.setItemAnimator(new DefaultItemAnimator());
        rvImage.setAdapter(adapter);
    }

    private void configGridView(ActivityEntity entity) {
        listImage = new ArrayList<ImageEntity>();
        if (entity != null) {
            if (!TextUtils.isEmpty(entity.getImages())) {
                String[] splitedStrings = entity.getImages().split(",");
                for (String url : splitedStrings) {
                    listImage.add(new ImageEntity(url));
                }
            }
        }
        listImage.addAll(imagesFromGallery);
        gridAdapter = new ImageGridAdapter(getActivity(), listImage,
                new ImageGridAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(ImageEntity item) {
                        if (item == null) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                                    getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                            } else {
                                //your code
                                showAlertDialog();
                            }
                        }
                    }

                });
        grid.setAdapter(gridAdapter);
    }

    private void configGridView() {
        listImage = new ArrayList<ImageEntity>();
        listImage.addAll(imagesFromGallery);
        gridAdapter = new ImageGridAdapter(getActivity(), listImage,
                new ImageGridAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(ImageEntity item) {
                        if (item == null) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                                    getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                            } else {
                                //your code
                                showAlertDialog();
                            }

                        }
                    }

                });
        grid.setAdapter(gridAdapter);
    }

    public void showAlertDialog() {
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


    @OnClick(R.id.img_calendar)
    public void showDateTimePicker() {
        Calendar myCalendar = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                myCalendar.set(Calendar.HOUR_OF_DAY, i);
                myCalendar.set(Calendar.MINUTE, i1);
                updateDateTimeTextView(myCalendar.getTime());
            }
        };

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                new TimePickerDialog(getActivity(), time, myCalendar.get(Calendar.HOUR_OF_DAY),
                        myCalendar.get(Calendar.MINUTE), false).show();

            }

        };

        new DatePickerDialog(getActivity(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateDateTimeTextView(Date date) {
        String myFormat = "hh:mm:ss dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        if (getActivity() != null)
            txtDateTime.setText(getResources().getString(R.string.text_at) + " " + sdf.format(date));
    }

    private void updateExpDateBuy(Date expDate) {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        txtExpDateBuy.setText(sdf.format(expDate));
    }

    @OnClick(R.id.img_calendar_buy)
    public void setCalenderBuy() {
        Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                txtExpDateBuy.setText(sdf.format(myCalendar.getTime()));
            }

        };

        new DatePickerDialog(getActivity(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_PICK_IMAGE) {
            try {
                final Uri imageUri = data.getData();
                listUri.add(imageUri);

                final InputStream imageStream = getActivity().getContentResolver()
                        .openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imagesFromGallery.add(new ImageEntity(selectedImage));
                //gridAdapter.setData(listImage);
                configGridView(item);

                File file = new File(getRealPathFromURI(imageUri));

                if (file != null) {
                    listFile.add(file);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_TAKE_IMAGE) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imagesFromGallery.add(new ImageEntity(photo));
            configGridView(item);
            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(getApplicationContext(), photo);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            File file = new File(getRealPathFromURI(tempUri));
            if (file != null) {
                listFile.add(file);
            }
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.clear();
                MarkerOptions options = new MarkerOptions();
                options.position(latLng);
                options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                mMap.addMarker(options);
                showAlertDialogMap();
            }
        });
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

    @Override
    public void setPresenter(EditDetailDiaryContract.Presenter presenter) {
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
        mPresenter.setActivityEntity((ActivityEntity) getActivity().getIntent().getSerializableExtra(
                Constants.KEY_EDIT_DETAIL_DIARY_ACTIVITY));
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.stop();
    }

    @Override
    public void showProductAndActon(ArrayList<String> listProduct, ArrayList<String> listAction) {
        this.listAction = listAction;
        this.listProduct = listProduct;
        ArrayList<String> newListAction = new ArrayList<>();
        ArrayList<String> newListProduct = new ArrayList<>();
        for (String action : listAction) {
            String[] string = action.split(" - ");
            newListAction.add(string[0]);
        }
        for (String product : listProduct) {
            String[] string = product.split(" - ");
            newListProduct.add(string[0]);
        }

        productAdapter = new ProductSpinnerAdapter(getActivity(), newListProduct);
        spinProduct.setAdapter(productAdapter);


        actionAdapter = new ActionSpinnerAdapter(getActivity(), newListAction,
                new ActionSpinnerAdapter.OnEditItemListener() {
                    @Override
                    public void onEditItem(String item) {
                        DeleteActionActivity.start(getActivity(), item);
                    }
                },
                new ActionSpinnerAdapter.OnAddItemListener() {
                    @Override
                    public void onAddItem() {
                        AddActionActivity.start(getActivity(), spinProduct.getSelectedItem().toString());
                    }
                });

        spinTypeAction.setAdapter(actionAdapter);

        spinTypeAction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (actionAdapter.getItem(position) != null) {
                    if (spinTypeAction.getSelectedItem().toString().contains("Mua phân bón/thuốc")) {
                        llBuy.setVisibility(View.VISIBLE);
                    } else {
                        llBuy.setVisibility(View.GONE);
                    }

                    if (spinTypeAction.getSelectedItem().toString().contains("Sử dụng phân bón/thuốc")) {
                        llUse.setVisibility(View.VISIBLE);
                    } else {
                        llUse.setVisibility(View.GONE);
                    }
                }

                if (llBuy.getVisibility() == View.GONE && llUse.getVisibility() == View.GONE) {
                    etNote.setImeOptions(EditorInfo.IME_ACTION_DONE);

                    etNote.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                            View view = getActivity().getCurrentFocus();
                            if (view != null) {
                                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            }
                            return false;
                        }
                    });
                } else {
                    if (llUse.getVisibility() == View.VISIBLE) {
                        etNote.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                                    etFieldIdUse.requestFocus();
                                }
                                return false;
                            }
                        });
                    }

                    if (llBuy.getVisibility() == View.VISIBLE) {
                        etNote.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                                    etFertilizerBuy.requestFocus();
                                }
                                return false;
                            }
                        });
                    }
                }
//                ImageView image = (ImageView) selectedItemView.findViewById(R.id.image);
//                image.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });


    }

    @Override
    public void showDetailDiary(ActivityEntity entity) {
        txtTitleToolbar.setText(getString(R.string.text_edit_detail_diary));
        Calendar calendar = Calendar.getInstance();
        item = entity;
        if (item == null) {
            updateDateTimeTextView(calendar.getTime());
        } else {
            isCreatingNewAcivity = false;
            for (int i = 0; i < listProduct.size(); i++) {
                if ((listProduct.get(i).contains(entity.getProductId()))) {
                    spinProduct.setSelection(i);
                    break;
                }
            }
            for (int i = 0; i < listAction.size(); i++) {
                if (listAction.get(i).contains(entity.getProductActionId())) {
                    spinTypeAction.setSelection(i);
                    break;
                }
            }
            if (item.getDate() != null) {
                calendar.setTime(item.getDate());
                updateDateTimeTextView(calendar.getTime());
            }
            configGridView(entity);
            etNote.setText(item.getNote());

            if (entity.getActivityBuyFertilizerEntity() != null) {
                showActivityBuyFertilizer(entity.getActivityBuyFertilizerEntity());
            }

            if (entity.getActivityUseFertilizerEntity() != null) {
                showActivityUseFertilizer(entity.getActivityUseFertilizerEntity());
            }
        }
    }

    @Override
    public void showActivityBuyFertilizer(ActivityBuyFertilizerEntity entity) {
        if (entity.getFertilizer() != null) {
            etFertilizerBuy.setText(entity.getFertilizer());
        }

        if (entity.getFertilizerId() != null) {
            etFertilizerIdBuy.setText(entity.getFertilizerId());
        }

        if (entity.getQuality() != null) {
            etQualityBuy.setText(entity.getQuality());
        }

        if (entity.getCompany() != null) {
            etCompany.setText(entity.getCompany());
        }

        if (entity.getStore() != null) {
            etStore.setText(entity.getStore());
        }

        if (entity.getExpDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
            Date expDate = null;
            try {
                expDate = format.parse(entity.getExpDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (expDate != null) {
                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                txtExpDateBuy.setText(sdf.format(expDate));
            }
        }

        if (entity.getQuantity() != null) {
            etQuantityBuy.setText(entity.getQuantity());
        }
    }

    @Override
    public void saveActivityBuyFertilizer(ActivityBuyFertilizerEntity entity) {
        entity.setFertilizer(etFertilizerBuy.getText().toString());

        entity.setFertilizerId(etFertilizerIdBuy.getText().toString());

        entity.setQuality(etQualityBuy.getText().toString());

        entity.setCompany(etCompany.getText().toString());

        entity.setStore(etStore.getText().toString());

        if (!TextUtils.isEmpty(txtExpDateBuy.getText())) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date expDate = Calendar.getInstance().getTime();
            try {
                expDate = format.parse(txtExpDateBuy.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (expDate != null) {
                String myFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                entity.setExpDate(sdf.format(expDate));
            }
        }

        try {
            entity.setQuantity(etQuantityBuy.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            entity.setQuantity("");
        }
    }

    public boolean isEmptyEdittextActivityBuy() {

        if (TextUtils.isEmpty(etFertilizerBuy.getText().toString())) {
            showErrorEmptyEdittext("Vui lòng nhập Loại phân bón/ thuốc");
            return true;
        }

        if (TextUtils.isEmpty(etFertilizerIdBuy.getText().toString())) {
            showErrorEmptyEdittext("Vui lòng nhập Mã số lô(thuốc)");
            return true;
        }

        if (TextUtils.isEmpty(etQualityBuy.getText().toString())) {
            showErrorEmptyEdittext("Vui lòng nhập Chất lượng");
            return true;
        }

        if (TextUtils.isEmpty(etCompany.getText().toString())) {
            showErrorEmptyEdittext("Vui lòng nhập Tên công ty");
            return true;
        }

        if (TextUtils.isEmpty(etStore.getText().toString())) {
            showErrorEmptyEdittext("Vui lòng nhập Cửa hàng");
            return true;
        }

        if (TextUtils.isEmpty(etQuantityBuy.getText().toString())) {
            showErrorEmptyEdittext("Vui lòng nhập Số lượng");
            return true;
        }
        return false;
    }

    @Override
    public void showActivityUseFertilizer(ActivityUseFertilizerEntity entity) {

        if (entity.getFieldId() != null) {
            etFieldIdUse.setText(entity.getFieldId());
        }

        if (entity.getFieldArea() != null) {
            etFieldAreaUse.setText(entity.getFieldArea());
        }

        if (entity.getFertilizer() != null) {
            etFertilizerUse.setText(entity.getFertilizer());
        }

        if (entity.getFertilizerId() != null) {
            etFertilizerIdUse.setText(entity.getFertilizerId());
        }

        if (entity.getDosage() != null) {
            etDosageUse.setText(entity.getDosage().toString());
        }

        if (entity.getQuantity() != null) {
            etQuantityUse.setText(entity.getQuantity());
        }

        if (entity.getStock() != null) {
            etStockUse.setText(entity.getStock());
        }

        if (entity.getGoal() != null) {
            etGoalUse.setText(entity.getGoal());
        }

        if (entity.getEquiment() != null) {
            etEquimentUse.setText(entity.getEquiment());
        }

        if (entity.getIsolationTime() != null) {
            etIsolationTimeUse.setText(entity.getIsolationTime().toString());
        }

//        if (entity.getIsolationTime() != null) {
//            etIsolationTimeUse.setText(entity.getIsolationTime());
//        }

        if (entity.getCleanedEquipment() != null) {
            if (entity.getCleanedEquipment()) {
                rbTrue.setSelected(true);
                rbFalse.setSelected(false);
            } else {
                rbTrue.setSelected(false);
                rbFalse.setSelected(true);
            }
        }
    }

    @Override
    public void saveActivityUseFertilizer(ActivityUseFertilizerEntity entity) {
        entity.setFieldId(etFieldIdUse.getText().toString());

        entity.setFieldArea(etFieldAreaUse.getText().toString());

        entity.setFertilizer(etFertilizerUse.getText().toString());

        entity.setFertilizerId(etFertilizerIdUse.getText().toString());

        try {
            entity.setDosage(etDosageUse.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            entity.setDosage("");
        }

        try {
            entity.setQuantity(etQuantityUse.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            entity.setQuantity("");
        }

        try {
            entity.setStock(etStockUse.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            entity.setStock("");
        }

        entity.setGoal(etGoalUse.getText().toString());

        entity.setEquiment(etEquimentUse.getText().toString());

        try {
            entity.setIsolationTime(Integer.parseInt(etIsolationTimeUse.getText().toString()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            entity.setIsolationTime(0);
        }

        if (rbTrue.isSelected()) {
            entity.setCleanedEquipment(true);
        } else {
            entity.setCleanedEquipment(false);
        }

    }

    public boolean isEmptyEdittextActivityUse() {
        if (TextUtils.isEmpty(etFieldIdUse.getText().toString())) {
            showErrorEmptyEdittext("Vui lòng nhập Mã số đất thừa");
            return true;
        }

        if (TextUtils.isEmpty(etFieldAreaUse.getText().toString())) {
            showErrorEmptyEdittext("Vui lòng nhập Diện tích");
            return true;
        }

        if (TextUtils.isEmpty(etFertilizerUse.getText().toString())) {
            showErrorEmptyEdittext("Vui lòng nhập Loại phân bón/thuốc");
            return true;
        }

        if (TextUtils.isEmpty(etFertilizerIdUse.getText().toString())) {
            showErrorEmptyEdittext("Vui lòng nhập Mã số lô(thuốc)");
            return true;
        }

        if (TextUtils.isEmpty(etDosageUse.getText().toString())) {
            showErrorEmptyEdittext("Vui lòng nhập Liều lượng");
            return true;
        }

        if (TextUtils.isEmpty(etQuantityUse.getText().toString())) {
            showErrorEmptyEdittext("Vui lòng nhập Số lượng");
            return true;
        }

        if (TextUtils.isEmpty(etStockUse.getText().toString())) {
            showErrorEmptyEdittext("Vui lòng nhập Tồn (kg/lít)");
            return true;
        }

        if (TextUtils.isEmpty(etGoalUse.getText().toString())) {
            showErrorEmptyEdittext("Vui lòng nhập Mục đích sử dụng");
            return true;
        }

        if (TextUtils.isEmpty(etEquimentUse.getText().toString())) {
            showErrorEmptyEdittext("Vui lòng nhập Thiết bị sử dụng");
            return true;
        }

        if (TextUtils.isEmpty(etIsolationTimeUse.getText().toString())) {
            showErrorEmptyEdittext("Vui lòng nhập Thời gian cách ly");
            return true;
        }
        return false;
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

    public void showErrorEmptyEdittext(String message) {
        Activity activity = getActivity();
        if (activity != null) {
            new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(getString(R.string.text_sweet_dialog_title))
                    .setContentText(message)
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
    public void finishActivity() {
        getActivity().finish();
    }

    @Override
    public void setResultNewActivity(ActivityEntity entity) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(Constants.NEW_ACTIVITY, entity);
        getActivity().setResult(Activity.RESULT_OK, returnIntent);
    }

    @Override
    public void setResultOldAndNewActivity(ActivityEntity oldEntity, ActivityEntity newEntity) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(Constants.NEW_ACTIVITY, newEntity);
        returnIntent.putExtra(Constants.OLD_ACTIVITY, oldEntity);
        getActivity().setResult(Activity.RESULT_OK, returnIntent);
    }


    @OnClick(R.id.img_add)
    public void addImage() {
        showAlertDialog();
    }

    @OnClick(R.id.img_back)
    public void back() {
        getActivity().finish();
    }

    @OnClick(R.id.txt_save)
    public void saveActivity() {
        if (isCreatingNewAcivity) {
            ActivityEntity entity = new ActivityEntity();
            for (ActionEntity action : ActionManager.getInstance().getListAction()) {
                if (spinTypeAction.getSelectedItem().toString().contains(action.getName())) {
                    entity.setProductActionId(action.getId());
                    if (spinTypeAction.getSelectedItem().toString().contains("Mua phân bón/thuốc")) {
                        if (isEmptyEdittextActivityBuy()) {
                            return;
                        }
                        ActivityBuyFertilizerEntity buyEntity = new ActivityBuyFertilizerEntity();
                        saveActivityBuyFertilizer(buyEntity);
                        entity.setActivityBuyFertilizerEntity(buyEntity);
                    }

                    if (spinTypeAction.getSelectedItem().toString().contains("Sử dụng phân bón/thuốc")) {
                        if (isEmptyEdittextActivityUse()) {
                            return;
                        }
                        ActivityUseFertilizerEntity useEntity = new ActivityUseFertilizerEntity();
                        saveActivityUseFertilizer(useEntity);
                        entity.setActivityUseFertilizerEntity(useEntity);
                    }
                    break;
                }
            }

            for (ProductEntity product : ProductManager.getInstance().getList()) {
                if (spinProduct.getSelectedItem().toString().contains(product.getName())) {
                    entity.setProductId(product.getId());
                    break;
                }
            }
            entity.setActivityActionId("bf98a610-f058-11e7-ac04-ed309759e1b7"); //Action ID of add activity
            entity.setNote(etNote.getText().toString());
            if (!TextUtils.isEmpty(txtDateTime.getText())) {
                SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
                Date expDate = Calendar.getInstance().getTime();
                try {
                    expDate = format.parse(txtDateTime.getText().toString()
                            .replace(getResources().getString(R.string.text_at), ""));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (expDate != null) {
                    String myFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                    entity.setDate(sdf.format(expDate));
                }
            }

            if (UserManager.getInstance().getEmployeeUser() != null) {
                entity.setAssigneeId(UserManager.getInstance().getEmployeeUser().getId());
                entity.setFarmerId(UserManager.getInstance().getSelectedFarmerId());
                entity.setEditor(UserManager.getInstance().getEmployeeUser().getId());
            } else {
                entity.setAssigneeId((String) UserManager.getInstance().getFarmerUser().getEmployeeId());
                entity.setFarmerId(UserManager.getInstance().getFarmerUser().getId());
                entity.setEditor(UserManager.getInstance().getFarmerUser().getId());
            }

            entity.setImages("");
            if (listFile.size() != 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    //your code
                    mPresenter.uploadFileAndCreateNewActivity(entity, listFile);
                }

            } else {

                mPresenter.createNewActivity(entity);
            }
        } else {
            //EDIT ACTIVITY
            ActivityEntity oldActivity = new ActivityEntity(item);
            for (ActionEntity action : ActionManager.getInstance().getListAction()) {
                if (spinTypeAction.getSelectedItem().toString().contains(action.getName())) {
                    item.setProductActionId(action.getId());
                    if (spinTypeAction.getSelectedItem().toString().contains("Mua phân bón/thuốc")) {
                        if (isEmptyEdittextActivityBuy()) {
                            return;
                        }
                        if (item.getActivityBuyFertilizerEntity() == null) {
                            ActivityBuyFertilizerEntity buyEntity = new ActivityBuyFertilizerEntity();
                            saveActivityBuyFertilizer(buyEntity);
                            item.setActivityBuyFertilizerEntity(buyEntity);
                        } else {
                            ActivityBuyFertilizerEntity buyEntity = item.getActivityBuyFertilizerEntity();
                            saveActivityBuyFertilizer(buyEntity);
                            item.setActivityBuyFertilizerEntity(buyEntity);
                        }
                    } else {
                        item.setActivityBuyFertilizerEntity(null);
                    }

                    if (spinTypeAction.getSelectedItem().toString().contains("Sử dụng phân bón/thuốc")) {
                        if (isEmptyEdittextActivityUse()) {
                            return;
                        }
                        if (item.getActivityUseFertilizerEntity() == null) {
                            ActivityUseFertilizerEntity useEntity = new ActivityUseFertilizerEntity();
                            saveActivityUseFertilizer(useEntity);
                            item.setActivityUseFertilizerEntity(useEntity);
                        } else {
                            ActivityUseFertilizerEntity useEntity = item.getActivityUseFertilizerEntity();
                            saveActivityUseFertilizer(useEntity);
                            item.setActivityUseFertilizerEntity(useEntity);
                        }
                    } else {
                        item.setActivityUseFertilizerEntity(null);
                    }
                    break;
                }
            }

            for (ProductEntity product : ProductManager.getInstance().getList()) {
                if (spinProduct.getSelectedItem().toString().contains(product.getName())) {
                    item.setProductId(product.getId());
                    break;
                }
            }
            item.setActivityActionId("c1eecb10-f058-11e7-ac04-ed309759e1b7"); //Action ID of edit activity
            item.setNote(etNote.getText().toString());

            if (!TextUtils.isEmpty(txtDateTime.getText())) {
                SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
                Date expDate = Calendar.getInstance().getTime();
                try {
                    expDate = format.parse(txtDateTime.getText().toString()
                            .replace(getResources().getString(R.string.text_at), ""));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (expDate != null) {
                    String myFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                    item.setDate(sdf.format(expDate));
                }
            }
            if (UserManager.getInstance().getEmployeeUser() != null) {
                item.setAssigneeId(UserManager.getInstance().getEmployeeUser().getId());
                item.setFarmerId(UserManager.getInstance().getSelectedFarmerId());
                item.setEditor(UserManager.getInstance().getEmployeeUser().getId());
            } else {
                item.setAssigneeId((String) UserManager.getInstance().getFarmerUser().getEmployeeId());
                item.setFarmerId(UserManager.getInstance().getFarmerUser().getId());
                item.setEditor(UserManager.getInstance().getFarmerUser().getId());
            }
            if (listFile.size() != 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    //your code
                    mPresenter.uploadFileAndEditActivity(item, listFile, oldActivity);
                }

            } else {
                mPresenter.editActivity(item, oldActivity);
            }
        }

    }
}
