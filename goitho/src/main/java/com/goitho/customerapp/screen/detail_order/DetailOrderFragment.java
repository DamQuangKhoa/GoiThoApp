package com.goitho.customerapp.screen.detail_order;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.architect.data.model.OrderEntity;
import com.demo.architect.data.model.offline.ImageEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.ImageAdapter;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.dialogs.CustomDialogCancelOrder;
import com.goitho.customerapp.dialogs.CustomDialogEditContentOrder;
import com.goitho.customerapp.dialogs.CustomDialogLibraryCapture;
import com.goitho.customerapp.dialogs.CustomDialogReasonCancel;
import com.goitho.customerapp.screen.rating.RatingActivity;
import com.goitho.customerapp.screen.support_center.SupportCenterActivity;
import com.goitho.customerapp.util.Precondition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by MSI on 26/11/2017.
 */

public class DetailOrderFragment extends BaseFragment implements DetailOrderContract.View {
    private final String TAG = DetailOrderFragment.class.getName();
    private DetailOrderContract.Presenter mPresenter;
    public static final int REQUEST_CODE_PICK_IMAGE = 666;
    public static final int REQUEST_CODE_TAKE_IMAGE = 667;
    private ImageAdapter adapter;

    @Bind(R.id.rv_image)
    RecyclerView rvImage;

    @Bind(R.id.cv_rating)
    CardView cvRating;

    @Bind(R.id.cv_status)
    CardView cvStatus;

    @Bind(R.id.cv_cancel)
    CardView cvCancel;

    @Bind(R.id.layout_rating)
    LinearLayout llRating;

    @Bind(R.id.txt_status)
    TextView txtStatus;

    @Bind(R.id.txt_title_price)
    TextView txtTitlePrice;

    @Bind(R.id.txt_repair_fee)
    TextView txtRepairFee;

    @Bind(R.id.layout_button)
    LinearLayout llButton;

    @Bind(R.id.layout_open)
    LinearLayout llOpen;

    @Bind(R.id.layout_cancel)
    LinearLayout llCancel;
    @Bind(R.id.txt_content)
    TextView txtContent;

    private OrderEntity order;

    public DetailOrderFragment() {
        // Required empty public constructor
    }

    public static DetailOrderFragment newInstance() {
        DetailOrderFragment fragment = new DetailOrderFragment();
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
        View view = inflater.inflate(R.layout.fragment_detail_order, container, false);
        ButterKnife.bind(this, view);
        order = (OrderEntity) getActivity().getIntent().getSerializableExtra(Constants.KEY_DETAIL_ORDER_ACTIVITY);
        initView();
        initRecyclerView();
        return view;
    }

    private void initView() {
        switch (order.getStatus()) {
            case 1:
                txtStatus.setText("Đặt hàng thành công");
                txtStatus.setTextColor(getContext().getResources().getColor(R.color.fadedOrangeTwo));
                txtRepairFee.setText("Chờ cập nhật.");
                txtRepairFee.setTextColor(getContext().getResources().getColor(R.color.fadedOrangeTwo));
                llOpen.setVisibility(GONE);
                cvCancel.setVisibility(GONE);
                txtTitlePrice.setText("Báo giá sửa chữa");
                cvRating.setVisibility(GONE);
                break;
            case 2:
                cvCancel.setVisibility(GONE);
                llOpen.setVisibility(GONE);
                if (order.getPoint() > 0) {

                    llRating.setVisibility(GONE);
                } else {
                    cvRating.setVisibility(GONE);
                }
                llCancel.setVisibility(GONE);
                break;
            case 3:
                cvStatus.setVisibility(GONE);
                cvRating.setVisibility(GONE);
                llButton.setVisibility(GONE);
                llCancel.setVisibility(GONE);
                break;
        }

    }

    private void initRecyclerView() {
        adapter = new ImageAdapter(new ArrayList<ImageEntity>(), getContext());
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvImage.setLayoutManager(layoutManager);
        rvImage.setAdapter(adapter);
    }


    @Override
    public void setPresenter(DetailOrderContract.Presenter presenter) {
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

    @OnClick(R.id.layout_rating)
    public void rating() {
        startRatingActivity();
    }

    @OnClick(R.id.layout_cancel)
    public void cancel() {
        startDialogCancelOrder();
    }

    @OnClick(R.id.layout_open_order)
    public void openOrder() {
        DetailOrderActivity.start(getActivity(), new OrderEntity("", "", 1, 0, ""));
        getActivity().finish();
    }

    @OnClick(R.id.img_edit_rating)
    public void editRating() {
        RatingActivity.start(getContext());
    }

    @OnClick(R.id.img_edit_content)
    public void editContent() {
        startDialogEditContentOrder();
    }

    @OnClick(R.id.img_add_image)
    public void addImage() {
        startDialogLibraryCapture();
    }

    @OnClick(R.id.layout_support_center)
    public void supportCenter(){
        SupportCenterActivity.start(getContext());
    }

    @Override
    public void showListImage(ArrayList<ImageEntity> mList) {
        adapter.setData(mList);
    }

    @Override
    public void startRatingActivity() {
        RatingActivity.start(getContext());
    }

    @Override
    public void startDialogCancelOrder() {
        CustomDialogCancelOrder dialog = new CustomDialogCancelOrder();
        dialog.show(getActivity().getFragmentManager(), TAG);
        dialog.setListener(new CustomDialogCancelOrder.OnCancelClickListener() {
            @Override
            public void onCancelClick() {
                DetailOrderActivity.start(getActivity(), new OrderEntity("", "", 3, 0, ""));
                getActivity().finish();
            }
        }, new CustomDialogCancelOrder.OnNoCheckListener() {
            @Override
            public void onNoCheck() {
                CustomDialogReasonCancel dialogReasonCancel = new CustomDialogReasonCancel();
                dialogReasonCancel.show(getActivity().getFragmentManager(),
                        TAG + "CustomDialogReasonCancel");
            }
        });
    }

    @Override
    public void startDialogEditContentOrder() {
        CustomDialogEditContentOrder dialog = new CustomDialogEditContentOrder();
        dialog.show(getActivity().getFragmentManager(), TAG);
        dialog.setListener(new CustomDialogEditContentOrder.OnSaveClickListener() {
            @Override
            public void onSaveClick(String content) {
                txtContent.setText(content);
            }

        });
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
        }

        return imageFile;
    }


}
