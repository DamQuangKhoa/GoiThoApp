package com.goitho.customerapp.screen.exhibition_done;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.architect.data.model.ExhibitionEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.ExhibitionDoneAdapter;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.app.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Skull on 27/11/2017.
 */

public class ExhibitionDoneFragment extends BaseFragment implements ExhibitionDoneContract.View {

    @Inject
    ExhibitionDonePresenter mPresenter;

    @Bind(R.id.lv_exhibition)
    ListView lvExhibition;

    private ExhibitionDoneAdapter adapter;

    public ExhibitionDoneFragment() {
        // Required empty public constructor
    }


    public static ExhibitionDoneFragment newInstance() {
        ExhibitionDoneFragment fragment = new ExhibitionDoneFragment();
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
        View view = inflater.inflate(R.layout.fragment_exhibition, container, false);
        configFragments();
        ButterKnife.bind(this, view);
        initListView();
        return view;
    }

    private void initListView() {
        adapter = new ExhibitionDoneAdapter(getActivity(), new ArrayList<ExhibitionEntity>(),
                new ExhibitionDoneAdapter.OnNextItemListener() {
                    @Override
                    public void onNextItem(int position) {

                    }
                });

        lvExhibition.setAdapter(adapter);
        lvExhibition.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }

    private void configFragments() {
        CoreApplication.getInstance().getApplicationComponent()
                .plus(new ExhibitionDoneModule(this))
                .inject(this);

    }

    @Override
    public void setPresenter(ExhibitionDoneContract.Presenter presenter) {

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
    public void showExhibitionDoneList(List<ExhibitionEntity> list) {
        adapter.setData(list);
    }
}
