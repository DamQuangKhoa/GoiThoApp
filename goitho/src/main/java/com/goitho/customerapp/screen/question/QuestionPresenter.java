package com.goitho.customerapp.screen.question;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.repository.base.local.LocalRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class QuestionPresenter implements QuestionContract.Presenter {

    private final String TAG = QuestionPresenter.class.getName();
    private final QuestionContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    QuestionPresenter(@NonNull QuestionContract.View view) {
        this.view = view;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        view.showQuetionListOnUI(loadQuestion(),loadAnswer(loadQuestion()));
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }

    @Override
    public ArrayList<String> loadQuestion() {
        ArrayList<String> listDataHeader = new ArrayList<String>();
        listDataHeader.add("Nếu không hài lòng với thợ hiện tại tôi có được đổi thợ mới không?");
        listDataHeader.add("Chế độ bảo hành khi sửa chữa như thế nào?");
        listDataHeader.add("Gọi Thợ cung cấp những dịch vụ nào?");
        listDataHeader.add("Tại sa tôi nên sử dụng app của Gọi Thợ");
        return listDataHeader;
    }

    @Override
    public HashMap<String, List<String>> loadAnswer(ArrayList<String> listQuestion) {
        HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();
        List<String> answer = new ArrayList<String>();
        answer.add("I’m not really sure how old I was when I got the gift for Christmas, " +
                "but I remember thinking it was a pretty impressive piece of electronic hardware." +
                " It was really cool looking");

        List<String> answer1 = new ArrayList<String>();
        answer1.add("I’m not really sure how old I was when I got the gift for Christmas, " +
                "but I remember thinking it was a pretty impressive piece of electronic hardware." +
                " It was really cool looking");

        List<String> answer2 = new ArrayList<String>();
        answer2.add("I’m not really sure how old I was when I got the gift for Christmas," +
                " but I remember thinking it was a pretty impressive piece of electronic hardware. " +
                "It was really cool looking");

        List<String> answer3 = new ArrayList<String>();
        answer3.add("I’m not really sure how old I was when I got the gift for Christmas, " +
                "but I remember thinking it was a pretty impressive piece of electronic hardware. " +
                "It was really cool looking");



        listDataChild.put(listQuestion.get(0), answer); // Header, Child data
        listDataChild.put(listQuestion.get(1), answer1);
        listDataChild.put(listQuestion.get(2), answer2);
        listDataChild.put(listQuestion.get(3), answer3);
        return listDataChild;
    }
}
