package com.goitho.customerapp.screen.blog;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class BlogModule {
    private final BlogContract.View BlogContractView;

    public BlogModule(BlogContract.View BlogContractView) {
        this.BlogContractView = BlogContractView;
    }

    @Provides
    @NonNull
    BlogContract.View provideBlogContractView() {
        return this.BlogContractView;
    }
}

