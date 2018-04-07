package com.goitho.customerapp.screen.question;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.QuestionAdapter;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.app.di.Precondition;
import com.goitho.customerapp.widgets.AnimatedExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 26/11/2017.
 */

public class QuestionFragment extends BaseFragment implements QuestionContract.View {

    private QuestionContract.Presenter mPresenter;

    QuestionAdapter questionAdapter;
    @Bind(R.id.elv_question)
    AnimatedExpandableListView elvQuestion;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public QuestionFragment() {
        // Required empty public constructor
    }


    public static QuestionFragment newInstance() {
        QuestionFragment fragment = new QuestionFragment();
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
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        questionAdapter = new QuestionAdapter(getActivity(), listDataHeader, listDataChild);
        elvQuestion.setAdapter(questionAdapter);
        elvQuestion.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (elvQuestion.isGroupExpanded(groupPosition)) {
                    elvQuestion.collapseGroupWithAnimation(groupPosition);
                    questionAdapter.setOpen(false, groupPosition);
                } else {
                    elvQuestion.expandGroupWithAnimation(groupPosition);
                    questionAdapter.setOpen(true, groupPosition);
                }
                return true;
            }
        });
        elvQuestion.setGroupIndicator(null);
    }

    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    @Override
    public void setPresenter(QuestionContract.Presenter presenter) {
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
    public void showQuetionListOnUI(ArrayList<String> listQuestion, HashMap<String, List<String>> listAnswer) {
        questionAdapter.setData(listQuestion, listAnswer);
    }
}
