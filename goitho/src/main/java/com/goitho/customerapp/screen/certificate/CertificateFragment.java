package com.goitho.customerapp.screen.certificate;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import com.demo.architect.data.model.CertificateEntity;
import com.demo.architect.data.model.offline.ImageEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.ImageAdapter;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.manager.UserManager;
import com.goitho.customerapp.util.Precondition;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Skull on 30/11/2017.
 */

public class CertificateFragment extends BaseFragment implements CertificateContract.View {

    private CertificateContract.Presenter mPresenter;

    public static final int REQUEST_CODE_PICK_IMAGE = 666;
    public static final int REQUEST_CODE_TAKE_IMAGE = 667;
    @Bind(R.id.img_add)
    ImageView imgAdd;

    @Bind(R.id.img_back)
    ImageView imgBack;

    @Bind(R.id.rv_image)
    RecyclerView rvImage;

    @Bind(R.id.et_name)
    EditText etName;

    @Bind(R.id.et_note)
    EditText etNote;

    ImageAdapter adapter;
    ArrayList<ImageEntity> listImage;
    ArrayList<ImageEntity> imagesFromGallery = new ArrayList<>();
    ArrayList<File> listFile = new ArrayList<>();
    CertificateEntity entity;


    public CertificateFragment() {
        // Required empty public constructor
    }


    public static CertificateFragment newInstance() {
        CertificateFragment fragment = new CertificateFragment();
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
        View view = inflater.inflate(R.layout.fragment_certificate, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView(entity);

        return view;
    }

    private void initRecyclerView(CertificateEntity entity) {
        listImage = new ArrayList<>();
        if(entity != null) {
            if (!TextUtils.isEmpty(entity.getImages())) {
                String[] splitedStrings = entity.getImages().split(",");
                for (String url : splitedStrings) {
                    listImage.add(new ImageEntity(url));
                }
            }
        }
        listImage.addAll(imagesFromGallery);
        adapter= new ImageAdapter(listImage, getActivity());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        rvImage.setHasFixedSize(true);
        rvImage.setLayoutManager(layoutManager);
        rvImage.setItemAnimator(new DefaultItemAnimator());
        rvImage.setAdapter(adapter);
    }

    public void showAlertDialog(){
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
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_PICK_IMAGE) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver()
                        .openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imagesFromGallery.add(new ImageEntity(selectedImage));
                //gridAdapter.setData(listImage);
                initRecyclerView(entity);

                File file = new File(getRealPathFromURI(imageUri));

                if(file != null) {
                    listFile.add(file);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_TAKE_IMAGE) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imagesFromGallery.add(new ImageEntity(photo));
            initRecyclerView(entity);
            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(getApplicationContext(), photo);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            File file = new File(getRealPathFromURI(tempUri));
            if(file != null) {
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
    public void setPresenter(CertificateContract.Presenter presenter) {
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

    @OnClick(R.id.img_add)
    public void addImage(){
        showAlertDialog();
    }

    @OnClick(R.id.img_back)
    public void back(){
        getActivity().finish();
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
    public void showCertificates(List<CertificateEntity> list) {
        if (list.size()>0) {
            entity = list.get(0);
            etName.setText("");
            etNote.setText("");
            listImage = new ArrayList<>();
            etName.setText(etName.getText() + entity.getName());
            etNote.setText(etNote.getText() + entity.getNote());
            if(!TextUtils.isEmpty(entity.getImages())) {
                String[] strings = entity.getImages().split(",");
                for (String string : strings) {
                    listImage.add(new ImageEntity(string));
                }
            }

            adapter.setData(listImage);
        }
    }

    @Override
    public void finishActivity() {
        getActivity().finish();
    }

    @OnClick(R.id.txt_save)
    public void saveCertificate (){
        if(isEmptyEdittext())
            return;
        if(entity == null) {
            getCertificateEntity();
            if (listFile.size() > 0) {
                mPresenter.uploadImage(entity, listFile, true);
            }else {
                mPresenter.createCertificate(entity);
            }
        } else {
            getCertificateEntity();
            if (listFile.size()>0) {
                mPresenter.uploadImage(entity, listFile, false);
            } else {
                mPresenter.editCertificate(entity);
            }

        }
    }

    public void getCertificateEntity() {
        if(entity == null) {
            entity = new CertificateEntity();
        }
        if (TextUtils.isEmpty(entity.getImages()))
            entity.setImages("");

        entity.setName(etName.getText().toString());
        entity.setNote(etNote.getText().toString());
        entity.setFarmerId(UserManager.getInstance().getFarmerUser().getId());
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

    public boolean isEmptyEdittext() {
        if(TextUtils.isEmpty(etName.getText())) {
            showErrorEmptyEdittext("Vui lòng nhập tên ");
            return true;
        }

        if(TextUtils.isEmpty(etNote.getText())) {
            showErrorEmptyEdittext("Vui lòng nhập ghi chú ");
            return true;
        }
        return false;
    }
}
