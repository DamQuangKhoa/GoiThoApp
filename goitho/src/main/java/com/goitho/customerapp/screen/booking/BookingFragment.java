package com.goitho.customerapp.screen.booking;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.ImageOrderAdapter;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.dialogs.CustomDialogPromotion;
import com.goitho.customerapp.screen.dashboard.DashboardActivity;
import com.goitho.customerapp.screen.dashboard.DashboardFragment;
import com.goitho.customerapp.screen.register.RegisterActivity;
import com.goitho.customerapp.util.Precondition;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Skull on 27/11/2017.
 */

public class BookingFragment extends BaseFragment implements BookingContract.View {

    private static final String TAG = BookingFragment.class.getName();
    private static final int REQUEST_CODE_TAKE_IMAGE = 113;
    private static final int REQUEST_CODE_PICK_IMAGE = 123;
    private BookingContract.Presenter mPresenter;
    private ImageOrderAdapter adapter;
    @Bind(R.id.layout1)
    LinearLayout layout1;

    @Bind(R.id.layout2)
    LinearLayout layout2;

    @Bind(R.id.layout_image)
    RelativeLayout rlImage;

    @Bind(R.id.layout_success)
    LinearLayout llSuccess;

    @Bind(R.id.view)
    View view;

    @Bind(R.id.img_back)
    ImageView imgBack;

    @Bind(R.id.txt_title_toolbar)
    TextView txtTitleToolbar;

    @Bind(R.id.txt_code)
    TextView txtCode;

    @Bind(R.id.img_promotion)
    ImageView imgPromotion;

    @Bind(R.id.edt_description)
    EditText edtDescripton;

    @Bind(R.id.txt_date)
    TextView txtDate;

    @Bind(R.id.txt_hour)
    TextView txtHour;

    @Bind(R.id.edt_name)
    EditText edtName;

    @Bind(R.id.edt_phone)
    EditText edtPhone;

    @Bind(R.id.edt_address)
    EditText edtAddress;

    @Bind(R.id.rv_image)
    RecyclerView rvImage;

    private int type;

    public BookingFragment() {
        // Required empty public constructor
    }


    public static BookingFragment newInstance() {
        BookingFragment fragment = new BookingFragment();
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
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        ButterKnife.bind(this, view);
        type = getActivity().getIntent().getIntExtra(BookingActivity.KEY_HOME, 0);
        initView();
        initRecyclerView();
        return view;
    }

    private void initView() {
        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (19 * scale + 0.5f);
        if (type == 1) {
            imgBack.setVisibility(View.VISIBLE);
        } else {
            txtTitleToolbar.setPadding(dpAsPixels, 0, 0, 0);
        }
    }

    private void initRecyclerView() {
        adapter = new ImageOrderAdapter(new ArrayList<Bitmap>(), getActivity(),
                new ImageOrderAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Bitmap itemAsABitmap) {
                        adapter.removeData(itemAsABitmap);
                    }
                });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvImage.setLayoutManager(layoutManager);
        rvImage.setItemAnimator(new DefaultItemAnimator());
        rvImage.setAdapter(adapter);
    }

    @Override
    public void setPresenter(BookingContract.Presenter presenter) {
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

    @OnClick(R.id.layout_continue)
    public void next() {
        layout2.setVisibility(View.VISIBLE);
        layout1.setVisibility(View.GONE);
    }

    @OnClick(R.id.layout_booking)
    public void booking() {
        mPresenter.booking(edtDescripton.getText().toString(), txtDate.getText().toString(),
                txtHour.getText().toString(), txtCode.getText().toString(), edtAddress.getText().toString(),
                edtPhone.getText().toString(), edtName.getText().toString(),adapter.getListAddedBitmaps());
    }

    @OnClick(R.id.layout_promotion)
    public void promotion() {
        startDialogPromotion();
    }

    @OnClick(R.id.img_choose_time)
    public void chooseTime() {
        final Calendar currentDate = Calendar.getInstance();
        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                String date = (dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth + "";
                String month = ((monthOfYear + 1) < 10) ? "0" + (monthOfYear + 1) : (monthOfYear + 1) + "";
                txtDate.setText(date + "/" + month + "/" + year);
                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        txtHour.setText(hourOfDay + ":" + minute + ":" + "00");
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    @OnClick(R.id.layout_capture)
    public void capture() {
        showAlertDialogImage(REQUEST_CODE_PICK_IMAGE, REQUEST_CODE_TAKE_IMAGE);
    }

    public void showAlertDialogImage(int codePickImage, int codeTakeImage) {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.text_add_image));
        builder.setCancelable(false);
        builder.setPositiveButton(getString(R.string.text_pick_picture),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, codePickImage);
                        dialogInterface.dismiss();
                    }
                });
        builder.setNegativeButton(getString(R.string.text_take_picture),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, codeTakeImage);
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

    @OnClick(R.id.layout_go_home)
    public void goHome() {
        if (type == 0) {
            Fragment parentFragment = getParentFragment();
            if (parentFragment != null && parentFragment instanceof DashboardFragment) {
                ((DashboardFragment) parentFragment).openHome();
            }
        } else {
            DashboardActivity.start(getContext());
            getActivity().finish();
        }
    }

    @OnClick(R.id.layout_register)
    public void register() {
        RegisterActivity.start(getContext());
    }

    @OnClick(R.id.img_back)
    public void back() {
        DashboardActivity.start(getContext());
        getActivity().finish();
    }

    @Override
    public void startBookingSuccess() {
        llSuccess.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.GONE);
        view.setVisibility(View.GONE);
    }

    @Override
    public void startDialogPromotion() {
        CustomDialogPromotion dialog = new CustomDialogPromotion();
        dialog.show(getActivity().getFragmentManager(), TAG);
        dialog.setListener(new CustomDialogPromotion.OnClickListener() {
            @Override
            public void onClick(String promotion) {
                if (!promotion.isEmpty()) {
                    mPresenter.checkSaleId(promotion);
                }
            }
        });
    }

    @Override
    public void showSaleId(String code) {
        txtCode.setText(code);
        imgPromotion.setImageResource(R.drawable.ic_add_promotion);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bitmap selectedImage = null;
            Uri uri = data.getData();
            if (uri == null) {
                selectedImage = (Bitmap) data.getExtras().get("data");
            } else {
                try {
                    InputStream imageStream = getActivity().getContentResolver()
                            .openInputStream(uri);
                    selectedImage = BitmapFactory.decodeStream(imageStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            if (selectedImage != null) {
                adapter.addData(selectedImage);
                rlImage.setVisibility(View.GONE);
                rvImage.setVisibility(View.VISIBLE);
            }
        }
    }


}
