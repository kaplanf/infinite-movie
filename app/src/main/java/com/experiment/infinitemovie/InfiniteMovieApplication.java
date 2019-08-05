package com.experiment.infinitemovie;

import android.app.Application;

import com.experiment.infinitemovie.data.DataManager;
import com.experiment.infinitemovie.di.component.ApplicationComponent;
import com.experiment.infinitemovie.di.component.DaggerApplicationComponent;
import com.experiment.infinitemovie.di.module.ApplicationModule;
import javax.inject.Inject;

public class InfiniteMovieApplication extends Application {

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
