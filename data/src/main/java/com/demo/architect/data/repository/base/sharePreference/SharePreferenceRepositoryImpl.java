package com.demo.architect.data.repository.base.sharePreference;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceRepositoryImpl implements SharePreferenceRepository {

    //private static final Logger log = LoggerFactory.getLogger(SharePreferenceRepository.class);

    private static final String PREFERENCE_MAIN = "com.demo.uyminhduc.MAIN";
    private static final String MY_PREFERENCE = "com.demo.uyminhduc.MAIN.MY_PREFERENCE";

    private final SharedPreferences sharedPreferences;

    public SharePreferenceRepositoryImpl(Application application) {
        this.sharedPreferences = application.getSharedPreferences(PREFERENCE_MAIN, Context.MODE_PRIVATE);
    }

    @Override
    public String readMyPreference() {
       // log.debug("readMyPreference");
        return sharedPreferences.getString(MY_PREFERENCE, "");
    }

    @Override
    public void writeMyPreference(String value) {
      //  log.debug("writeMyPreference: {}", value);
        sharedPreferences.edit().putString(MY_PREFERENCE, value).apply();
    }
}
