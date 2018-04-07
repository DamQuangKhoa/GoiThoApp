package com.goitho.customerapp.screen.question;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {QuestionModule.class})
public interface QuestionComponent {
    void inject(QuestionActivity activity);

}
