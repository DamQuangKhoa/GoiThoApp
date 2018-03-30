package com.goitho.customerapp.screen.blog;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by MSI on 26/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {BlogModule.class})
public interface BlogComponent {
    void inject(BlogActivity activity);

}
