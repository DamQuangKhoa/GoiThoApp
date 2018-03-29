package com.goitho.customerapp.screen.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.StackView;

import com.demo.architect.data.model.BlogEntity;
import com.demo.architect.data.model.OrderEntity;
import com.demo.architect.data.model.RatingEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.BlogAdapter;
import com.goitho.customerapp.adapter.RatingAdapter;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.screen.detail_order.DetailOrderActivity;
import com.goitho.customerapp.util.Precondition;
import com.goitho.customerapp.widgets.customStackView.CustomStackView;

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

    private BlogAdapter blogAdapter;

    @Bind(R.id.rv_rating)
    RecyclerView rvRating;

    private RatingAdapter ratingAdapter;

    @Bind(R.id.img_cover)
    ImageView imgCover;

    @Bind(R.id.layout_content)
    LinearLayout layout;

    @Bind(R.id.sv_blog)
    CustomStackView svBlog;

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
        final RelativeLayout rlOrderRepair = (RelativeLayout) view.findViewById(R.id.layout_order_repair);

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
    public void order(){
        startDetailOrder();
    }
    @Override
    public void showRatingList(ArrayList<RatingEntity> list) {
        ratingAdapter.setData(list);
    }

    @Override
    public void showBlogList(List<BlogEntity> list) {
        blogAdapter = new BlogAdapter(getContext(),list,
                new BlogAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }
                });
        svBlog.initStack(3);
        svBlog.setAdapter(blogAdapter);
    }

    @Override
    public void startDetailOrder() {
        DetailOrderActivity.start(getActivity(), new OrderEntity("", "", 1, 0, ""));
      
    }
}
