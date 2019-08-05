package com.experiment.infinitemovie.di.component;

import com.experiment.infinitemovie.di.PerActivity;
import com.experiment.infinitemovie.di.module.ActivityModule;
import com.experiment.infinitemovie.ui.latest.RecentFragment;
import com.experiment.infinitemovie.ui.main.MainActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(RecentFragment fragment);
}
