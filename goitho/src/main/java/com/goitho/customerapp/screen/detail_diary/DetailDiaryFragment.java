package com.goitho.customerapp.screen.detail_diary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.architect.data.helper.DateHelper;
import com.demo.architect.data.model.ActivityEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.DetailDiaryAdapter;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.screen.edit_detail_diary.EditDetailDiaryActivity;
import com.goitho.customerapp.screen.history.HistoryActivity;
import com.goitho.customerapp.util.Precondition;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Skull on 29/11/2017.
 */

public class DetailDiaryFragment extends BaseFragment implements DetailDiaryContract.View {

    private DetailDiaryContract.Presenter mPresenter;

    private DetailDiaryAdapter adapter;
    List<ActivityEntity> list = new ArrayList<>();
    private int  diaryPosition;

    @Bind(R.id.rv_detail_diary)
    RecyclerView rvDetailDiary;

    @Bind(R.id.txt_toolbar)
    TextView txtToolbar;

    //NOTE: CHƯA LOAD DATA Ở PRESENTER

    public DetailDiaryFragment() {
        // Required empty public constructor
    }


    public static DetailDiaryFragment newInstance() {
        DetailDiaryFragment fragment = new DetailDiaryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constants.KEY_RESULT && resultCode == Activity.RESULT_OK) {
            //some code
            ActivityEntity newActivity = (ActivityEntity) data.getSerializableExtra(Constants.NEW_ACTIVITY);
            ActivityEntity oldActivity  = (ActivityEntity) data.getSerializableExtra(Constants.OLD_ACTIVITY);

            if(oldActivity != null) {
                if (DateHelper.getZeroTimeDate(newActivity.getDate())
                        .compareTo(DateHelper.getZeroTimeDate(oldActivity.getDate()))==0) {
                    for (int i = 0; i< list.size(); i++) {
                        if(oldActivity.getId().equals(list.get(i).getId())) {
                            list.set(i, newActivity);
                            adapter.setData(list);
                            mPresenter.setListActivity(list);
                            break;
                        }
                    }
                } else {
                    for (int i = 0; i< list.size(); i++) {
                        if(oldActivity.getId().equals(list.get(i).getId())) {
                            list.remove(i);
                            adapter.setData(list);
                            mPresenter.setListActivity(list);
                            break;
                        }
                    }
                }
            } else {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
                Date now = calendar.getTime();
                if(txtToolbar.getText().toString().contains(dt.format(newActivity.getDate()))) {
                    list.add(newActivity);
                    adapter.setData(list);
                    mPresenter.setListActivity(list);
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_diary, container, false);
        ButterKnife.bind(this, view);
        //configToolbar();
        initRecyclerView();

        return view;
    }

    private void configToolbar(Date date) {
        if (date != null) {
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
            txtToolbar.setText(getString(R.string.text_diary_day) + " " + dt.format(date));
        }
    }


    private void initRecyclerView() {

        adapter = new DetailDiaryAdapter(new ArrayList<>(),
                new DetailDiaryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ActivityEntity item, int position) {
                //EditDetailDiaryActivity.start(getActivity(), item);
                Intent intent = new Intent(getActivity(), EditDetailDiaryActivity.class);
                intent.putExtra(Constants.KEY_EDIT_DETAIL_DIARY_ACTIVITY, item);
                startActivityForResult(intent, Constants.KEY_RESULT);
            }

            @Override
            public void onHistoryClick(ActivityEntity item, int position) {
                HistoryActivity.start(getActivity(), item);
            }

            @Override
            public void onDeleteClick(ActivityEntity item, int position) {
                mPresenter.deleteActivity(item);
            }
        }, getActivity());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvDetailDiary.setLayoutManager(layoutManager);
        rvDetailDiary.setItemAnimator(new DefaultItemAnimator());
        rvDetailDiary.setAdapter(adapter);
    }

    @Override
    public void setPresenter(DetailDiaryContract.Presenter presenter) {
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
    public void showDetailDiary(List<ActivityEntity> list) {
        adapter.setData(list);
        this.list=list;
        if( list.size() > 0)
            configToolbar(list.get(0).getDate());
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

    @OnClick(R.id.img_add_diary)
    public void addDiary(){
        //EditDetailDiaryActivity.start(getActivity());
        Intent intent = new Intent(getActivity(), EditDetailDiaryActivity.class);
        startActivityForResult(intent, Constants.KEY_RESULT);
    }



    @OnClick(R.id.img_back)
    public void back(){
        getActivity().finish();
    }

}
