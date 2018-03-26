package com.goitho.customerapp.screen.notification;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.architect.data.model.NotificationEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.NotificationAdapter;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.dialogs.CustomDialogNotification;
import com.goitho.customerapp.util.Precondition;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by MSI on 26/11/2017.
 */

public class NotificationFragment extends BaseFragment implements NotificationContract.View {
    private final String TAG = NotificationFragment.class.getName();
    private NotificationContract.Presenter mPresenter;

    private NotificationAdapter adapter;
    @Bind(R.id.lv_notification)
    ListView lvNotification;

    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance() {
        NotificationFragment fragment = new NotificationFragment();
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
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, view);
        initListView();
        return view;
    }

    private void initListView() {
        adapter = new NotificationAdapter(getContext(), new ArrayList<NotificationEntity>(),
                new NotificationAdapter.OnNextItemListener() {
                    @Override
                    public void onNextItem(int position) {
                        startDialogNotification();
                    }
                });
        lvNotification.setAdapter(adapter);
        lvNotification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startDialogNotification();
            }
        });
    }


    @Override
    public void setPresenter(NotificationContract.Presenter presenter) {
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


    @Override
    public void startDialogNotification() {
        CustomDialogNotification dialog = new CustomDialogNotification();
        dialog.show(getActivity().getFragmentManager(), TAG);
    }

    @Override
    public void showListNotification(List<NotificationEntity> mList) {
        adapter.setData(mList);
    }
}
