package com.goitho.customerapp.app.di.module;

import android.content.Context;

import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.app.di.JobExecutor;
import com.goitho.customerapp.app.di.PostExecutionThread;
import com.goitho.customerapp.app.di.ThreadExecutor;
import com.goitho.customerapp.app.di.UIThread;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by uyminhduc on 12/16/16.
 */

@Module
public class ApplicationModule {
    private final CoreApplication application;

    public ApplicationModule(CoreApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }
}