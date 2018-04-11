package com.goitho.customerapp.screen.edit_address;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.dialogs.CustomDialogLibraryCapture;
import com.goitho.customerapp.screen.register_success.RegisterSuccessActivity;
import com.goitho.customerapp.util.Precondition;
import com.goitho.customerapp.widgets.CircleTransform;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Skull on 29/11/2017.
 */

public class EditAddressFragment extends BaseFragment implements EditAddressContract.View {

    private static final String TAG = EditAddressFragment.class.getName();
    private EditAddressContract.Presenter mPresenter;
    public static final int REQUEST_CODE_PICK_IMAGE = 666;
    public static final int REQUEST_CODE_TAKE_IMAGE = 667;
    @Bind(R.id.et_name)
    EditText etName;

    @Bind(R.id.et_address1)
    EditText etAddress1;

    @Bind(R.id.et_address2)
    EditText etAddress2;

    @Bind(R.id.et_email)
    EditText etEmail;

    @Bind(R.id.img_avatar)
    ImageView imgAvatar;

    public EditAddressFragment() {
        // Required empty public constructor
    }


    public static EditAddressFragment newInstance() {
        EditAddressFragment fragment = new EditAddressFragment();
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
        View view = inflater.inflate(R.layout.fragment_edit_address, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    @OnClick(R.id.btn_complete)
    public void complete() {
        startRegisterSuccessActivity();
        getActivity().finish();
    }

    @OnClick(R.id.layoutCapture)
    public void capture() {
        startDialogLibraryCapture();
    }

    @Override
    public void setPresenter(EditAddressContract.Presenter presenter) {
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

    @OnClick(R.id.img_back)
    public void back() {
        getActivity().finish();
    }

    @Override
    public void startRegisterSuccessActivity() {
        RegisterSuccessActivity.start(getContext());
    }

    @Override
    public void startDialogLibraryCapture() {
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

    @Override
    public void showError() {

    }

    @Override
    public void startCamera() {

        Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, REQUEST_CODE_TAKE_IMAGE);
    }

    @Override
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
            Picasso.with(getContext()).load(imageUri).transform(new CircleTransform()).into(imgAvatar);

        }

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_TAKE_IMAGE) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Picasso.with(getContext()).load(persistImage(photo, "yenyen")).transform(new CircleTransform()).into(imgAvatar);
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
