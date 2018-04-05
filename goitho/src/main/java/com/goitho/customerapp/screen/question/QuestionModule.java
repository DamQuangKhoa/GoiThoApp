package com.goitho.customerapp.screen.question;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class QuestionModule {
    private final QuestionContract.View PostContractView;

    public QuestionModule(QuestionContract.View PostContractView) {
        this.PostContractView = PostContractView;
    }

    @Provides
    @NonNull
    QuestionContract.View providePostContractView() {
        return this.PostContractView;
    }
}

