package com.goitho.customerapp.screen.history;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 14/12/2017.
 */

@ActivityScope
@Subcomponent(modules = {HistoryModule.class})
public interface HistoryComponent {
    void inject(HistoryActivity activity);

}
