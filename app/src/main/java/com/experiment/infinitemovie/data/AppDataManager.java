package com.experiment.infinitemovie.data;


import android.content.Context;
import com.experiment.infinitemovie.data.network.ApiHeader;
import com.experiment.infinitemovie.data.network.ApiHelper;
import com.experiment.infinitemovie.data.network.model.MovieResponse;
import com.experiment.infinitemovie.data.prefs.PreferencesHelper;
import com.experiment.infinitemovie.di.ApplicationContext;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Observable<MovieResponse> getUpcoming(String apikey,String page) {
        return mApiHelper.getUpcoming(apikey,page);
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public void updateApiHeader(String accessToken) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }
}
