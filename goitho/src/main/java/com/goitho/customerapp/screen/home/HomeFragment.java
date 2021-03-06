package com.goitho.customerapp.screen.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.demo.architect.data.model.OrderEntity;
import com.demo.architect.data.model.PostEntity;
import com.demo.architect.data.model.RatingEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.RatingAdapter;
import com.goitho.customerapp.adapter.StackAdapter;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.dialogs.CustomDialogBooking;
import com.goitho.customerapp.screen.blog.BlogActivity;
import com.goitho.customerapp.screen.booking.BookingActivity;
import com.goitho.customerapp.screen.detail_order.DetailOrderActivity;
import com.goitho.customerapp.screen.post.PostActivity;
import com.goitho.customerapp.screen.promotion.PromotionActivity;
import com.goitho.customerapp.util.Precondition;
import com.goitho.customerapp.widgets.customStackView.Align;
import com.goitho.customerapp.widgets.customStackView.Config;
import com.goitho.customerapp.widgets.customStackView.StackLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 26/11/2017.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {
    private final String TAG = HomeFragment.class.getName();
    private HomeContract.Presenter mPresenter;

    private RatingAdapter ratingAdapter;
    private StackAdapter stackAdapter;

    @Bind(R.id.rv_rating)
    RecyclerView rvRating;

    @Bind(R.id.img_cover)
    ImageView imgCover;

    @Bind(R.id.layout_content)
    LinearLayout layout;

    @Bind(R.id.rv_blog)
    RecyclerView rvBlog;

    private int heightLayout = 0;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        initStackView();
        setView(view);
        return view;
    }

    private void initStackView() {


    }

    private void setView(View view) {
        int heightCover = imgCover.getDrawable().getIntrinsicHeight();
        final RelativeLayout rlOrderRepair = (RelativeLayout) view.findViewById(R.id.layout_order_booking);

        rlOrderRepair.post(new Runnable() {
            @Override
            public void run() {
                int height = rlOrderRepair.getHeight(); //height is ready
                heightLayout = height;
                RelativeLayout.LayoutParams relativeParams = (RelativeLayout.LayoutParams) layout.getLayoutParams();
                relativeParams.setMargins(0, (heightCover - (heightLayout / 2)), 0, 0);  // left, top, right, bottom
                layout.setLayoutParams(relativeParams);
            }
        });

    }

    private void initRecyclerView() {
        ratingAdapter = new RatingAdapter(new ArrayList<RatingEntity>(), getContext());
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvRating.setLayoutManager(layoutManager);
        rvRating.setAdapter(ratingAdapter);
    }


    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
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

    @OnClick(R.id.cv_order)
    public void order() {
        startDetailOrder();
    }

    @OnClick(R.id.btn_blog)
    public void blog() {
        BlogActivity.start(getContext());
    }

    @OnClick(R.id.layout_promotion)
    public void promotion() {
        PromotionActivity.start(getActivity());
    }

    @OnClick(R.id.layout_order_booking)
    public void booking() {
        BookingActivity.start(getContext());
    }

    @OnClick(R.id.layout_booking_hotline)
    public void bookingHotline() {
        CustomDialogBooking dialog = new CustomDialogBooking();
        dialog.show(getActivity().getFragmentManager(), TAG);
    }

    @Override
    public void showRatingList(ArrayList<RatingEntity> list) {
        ratingAdapter.setData(list);
    }

    @Override
    public void showBlogList(List<PostEntity> list) {
        Config config = new Config();
        config.secondaryScale = 0.8f;
        config.scaleRatio = 0.4f;
        config.maxStackCount = 3;
        config.initialStackCount = (list.size() - 1);
        config.space = getResources().getDimensionPixelOffset(R.dimen.distance_30dp);
        config.align = Align.RIGHT;
        rvBlog.setLayoutManager(new StackLayoutManager(config));
        stackAdapter = new StackAdapter(list, getContext(), new StackAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PostEntity item) {
                PostActivity.start(getContext(), item);
            }
        });
        rvBlog.setAdapter(stackAdapter);
        rvBlog.setHasFixedSize(false);
    }

    @Override
    public void startDetailOrder() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setStatus(1);
        DetailOrderActivity.start(getActivity(), orderEntity);

    }
}
