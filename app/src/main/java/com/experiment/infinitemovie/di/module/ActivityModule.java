package com.experiment.infinitemovie.di.module;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.experiment.infinitemovie.data.network.model.MovieObject;
import com.experiment.infinitemovie.di.ActivityContext;
import com.experiment.infinitemovie.di.PerActivity;
import com.experiment.infinitemovie.ui.detail.DetailMvpPresenter;
import com.experiment.infinitemovie.ui.detail.DetailMvpView;
import com.experiment.infinitemovie.ui.detail.DetailPresenter;
import com.experiment.infinitemovie.ui.latest.RecentMvpPresenter;
import com.experiment.infinitemovie.ui.latest.RecentMvpView;
import com.experiment.infinitemovie.ui.latest.RecentPresenter;
import com.experiment.infinitemovie.ui.main.MainMvpPresenter;
import com.experiment.infinitemovie.ui.main.MainMvpView;
import com.experiment.infinitemovie.ui.main.MainPresenter;
import com.experiment.infinitemovie.ui.main.MovieListAdapter;
import com.experiment.infinitemovie.util.rx.AppSchedulerProvider;
import com.experiment.infinitemovie.util.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import java.util.ArrayList;


@Module
public class ActivityModule {

  private AppCompatActivity mActivity;

  public ActivityModule(AppCompatActivity activity) {
    this.mActivity = activity;
  }

  @Provides
  @ActivityContext
  Context provideContext() {
    return mActivity;
  }

  @Provides
  AppCompatActivity provideActivity() {
    return mActivity;
  }

  @Provides
  CompositeDisposable provideCompositeDisposable() {
    return new CompositeDisposable();
  }

  @Provides
  SchedulerProvider provideSchedulerProvider() {
    return new AppSchedulerProvider();
  }

  @Provides
  LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
    return new LinearLayoutManager(activity);
  }

  @Provides
  @PerActivity
  MainMvpPresenter<MainMvpView> provideMainPresenter(
      MainPresenter<MainMvpView> presenter) {
    return presenter;
  }

  @Provides
  RecentMvpPresenter<RecentMvpView> provideRecentPresenter(
      RecentPresenter<RecentMvpView> presenter) {
    return presenter;
  }

  @Provides
  DetailMvpPresenter<DetailMvpView> provideDetailPresenter(
      DetailPresenter<DetailMvpView> presenter){
    return presenter;
  }

  @Provides
  MovieListAdapter provideMovieListAdapter(AppCompatActivity appCompatActivity) {
    return new MovieListAdapter(new ArrayList<>(),appCompatActivity);
  }
}
