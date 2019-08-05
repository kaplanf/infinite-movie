package com.experiment.infinitemovie.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import com.experiment.infinitemovie.di.ApplicationContext;
import com.experiment.infinitemovie.di.PreferenceInfo;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }
}
