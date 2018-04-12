package com.goitho.customerapp.screen.rating;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.dialogs.CustomDialogRating;
import com.goitho.customerapp.util.Precondition;
import com.goitho.customerapp.widgets.CircleTransform;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 26/11/2017.
 */

public class RatingFragment extends BaseFragment implements RatingContract.View {
    private final String TAG = RatingFragment.class.getName();
    private RatingContract.Presenter mPresenter;

    @Bind(R.id.img_avatar)
    ImageView imgAvatar;

    @Bind(R.id.ratingBar)
    RatingBar ratingBar;

    public RatingFragment() {
        // Required empty public constructor
    }

    public static RatingFragment newInstance() {
        RatingFragment fragment = new RatingFragment();
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
        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        ButterKnife.bind(this, view);
        initRatingBar();
        return view;
    }

    private void initRatingBar() {
    }


    @Override
    public void setPresenter(RatingContract.Presenter presenter) {
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

    @OnClick(R.id.btn_send)
    public void send() {
        startDialogRating();
    }

    @OnClick(R.id.img_back)
    public void back() {
        getActivity().finish();
    }

    @Override
    public void startDialogRating() {
        CustomDialogRating dialog = new CustomDialogRating();
        dialog.show(getActivity().getFragmentManager(), TAG);
    }

    @Override
    public void showProfileTho() {
        Picasso.with(getContext()).load(R.drawable.image).transform(new CircleTransform()).into(imgAvatar);
    }
}
