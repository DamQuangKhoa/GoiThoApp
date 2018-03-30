package com.goitho.customerapp.screen.post;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class PostModule {
    private final PostContract.View PostContractView;

    public PostModule(PostContract.View PostContractView) {
        this.PostContractView = PostContractView;
    }

    @Provides
    @NonNull
    PostContract.View providePostContractView() {
        return this.PostContractView;
    }
}

