package com.experiment.infinitemovie.di.component;

import android.app.Application;
import android.content.Context;

import com.experiment.infinitemovie.InfiniteMovieApplication;
import com.experiment.infinitemovie.data.DataManager;
import com.experiment.infinitemovie.di.ApplicationContext;
import com.experiment.infinitemovie.di.module.ApplicationModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(InfiniteMovieApplication app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}