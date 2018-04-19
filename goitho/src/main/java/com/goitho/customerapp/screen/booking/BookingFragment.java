package com.goitho.customerapp.screen.booking;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.dialogs.CustomDialogLibraryCapture;
import com.goitho.customerapp.dialogs.CustomDialogPromotion;
import com.goitho.customerapp.screen.dashboard.DashboardActivity;
import com.goitho.customerapp.screen.dashboard.DashboardFragment;
import com.goitho.customerapp.screen.register.RegisterActivity;
import com.goitho.customerapp.util.Precondition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Skull on 27/11/2017.
 */

public class BookingFragment extends BaseFragment implements BookingContract.View {

    private static final String TAG = BookingFragment.class.getName();
    private static final int REQUEST_CODE_TAKE_IMAGE = 113;
    private static final int REQUEST_CODE_PICK_IMAGE = 123;
    private BookingContract.Presenter mPresenter;

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
        startBookingSuccess();
    }

    @OnClick(R.id.layout_promotion)
    public void promotion() {
        startDialogPromotion();
    }

    @OnClick(R.id.layout_capture)
    public void capture() {
        CustomDialogLibraryCapture dialog = new CustomDialogLibraryCapture();
        dialog.show(getActivity().getFragmentManager(), TAG);
        dialog.setListener(new CustomDialogLibraryCapture.OnOpenCameraListener() {
            @Override
            public void onOpenCamera() {
                startCamera();
            }
        }, new CustomDialogLibraryCapture.OnOpenGalleryListener() {
            @Override
            public void onOpenGallery() {
                startGallery();
            }
        });
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
                    txtCode.setText(promotion);
                    imgPromotion.setImageResource(R.drawable.ic_add_promotion);
                }
            }
        });
    }

    public void startCamera() {

        Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, REQUEST_CODE_TAKE_IMAGE);
    }

    public void startGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_CODE_PICK_IMAGE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_PICK_IMAGE) {
            final Uri imageUri = data.getData();
            // Picasso.with(getContext()).load(imageUri).transform(new CircleTransform()).into(imgAvatar);

        }

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_TAKE_IMAGE) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            //Picasso.with(getContext()).load(persistImage(photo, "yenyen")).transform(new CircleTransform()).into(imgAvatar);

        }
    }

    private static File persistImage(Bitmap bitmap, String name) {
        File filesDir = getApplicationContext().getFilesDir();
        File imageFile = new File(filesDir, name + ".png");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.e(TAG, "Error writing bitmap", e);
        }

        return imageFile;
    }

}
