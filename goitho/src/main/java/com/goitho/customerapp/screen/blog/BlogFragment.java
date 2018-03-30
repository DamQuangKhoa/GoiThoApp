package com.goitho.customerapp.screen.blog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.demo.architect.data.model.PostEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.BlogAdapter;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.app.di.Precondition;
import com.goitho.customerapp.screen.post.PostActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 26/11/2017.
 */

public class BlogFragment extends BaseFragment implements BlogContract.View {

    private BlogContract.Presenter mPresenter;

    private BlogAdapter adapter;

    @Bind(R.id.layout_cover)
    RelativeLayout layout;

    @Bind(R.id.img_cover_blog)
    ImageView imgCover;

    @Bind(R.id.rv_blog)
    RecyclerView rvBlog;

    public BlogFragment() {
        // Required empty public constructor
    }


    public static BlogFragment newInstance() {
        BlogFragment fragment = new BlogFragment();
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
        View view = inflater.inflate(R.layout.fragment_blog, container, false);
        ButterKnife.bind(this, view);
        setView();
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        adapter = new BlogAdapter(new ArrayList<PostEntity>(), getContext(), new BlogAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PostEntity item) {
                PostActivity.start(getContext(), item);
            }
        });
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext());
        rvBlog.setLayoutManager(layoutManager);
        rvBlog.setAdapter(adapter);
    }

    private void setView() {
        int heightCover = imgCover.getDrawable().getIntrinsicHeight();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, heightCover);
        layout.setLayoutParams(layoutParams);
    }

    @Override
    public void setPresenter(BlogContract.Presenter presenter) {
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
    public void showBlogList(List<PostEntity> list) {
        adapter.setData(list);
    }
}
