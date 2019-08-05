package com.experiment.infinitemovie.di.module;

import android.app.Application;
import android.content.Context;

import com.experiment.infinitemovie.data.AppDataManager;
import com.experiment.infinitemovie.data.DataManager;
import com.experiment.infinitemovie.data.network.ApiHeader;
import com.experiment.infinitemovie.data.network.ApiHelper;
import com.experiment.infinitemovie.data.network.AppApiHelper;
import com.experiment.infinitemovie.data.prefs.AppPreferencesHelper;
import com.experiment.infinitemovie.data.prefs.PreferencesHelper;
import com.experiment.infinitemovie.di.ApplicationContext;
import com.experiment.infinitemovie.di.DatabaseInfo;
import com.experiment.infinitemovie.di.PreferenceInfo;
import com.experiment.infinitemovie.util.AppConstants;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                preferencesHelper.getAccessToken());
    }

}
