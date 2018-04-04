package com.goitho.customerapp.screen.post;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {PostModule.class})
public interface PostComponent {
    void inject(PostActivity activity);

}
