package com.goitho.customerapp.screen.question;


import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by MSI on 26/11/2017.
 */

public interface QuestionContract {
    interface View extends BaseView<Presenter> {
        void showQuetionListOnUI(ArrayList<String> listQuestion, HashMap<String, List<String>> listAnswer);
    }

    interface Presenter extends BasePresenter {
        ArrayList<String> loadQuestion();
        HashMap<String, List<String>> loadAnswer(ArrayList<String> listQuestion);
    }
}
