package com.experiment.infinitemovie.ui.latest;

import com.experiment.infinitemovie.data.DataManager;
import com.experiment.infinitemovie.ui.base.BasePresenter;
import com.experiment.infinitemovie.util.AppConstants;
import com.experiment.infinitemovie.util.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;

public class RecentPresenter<V extends RecentMvpView> extends BasePresenter<V> implements
    RecentMvpPresenter<V> {

  private static final String TAG = "RecentPresenter";

  @Inject
  public RecentPresenter(DataManager dataManager,
      SchedulerProvider schedulerProvider,
      CompositeDisposable compositeDisposable) {
    super(dataManager, schedulerProvider, compositeDisposable);
  }

  @Override
  public void getLatestMovies(int page) {
    String pageString = Integer.toString(page);
    getMvpView().showLoading();
    getCompositeDisposable().add(
        getDataManager().getUpcoming(AppConstants.API_KEY, pageString)
            .subscribeOn(getSchedulerProvider().io())
            .observeOn(getSchedulerProvider().ui()).subscribe(
            movieResponse -> {
              getMvpView().hideLoading();
              if (page > 1) {
                getMvpView().addMovies(movieResponse.getResults());
              } else {
                getMvpView().loadMovies(movieResponse.getResults());
              }
            }, throwable -> getMvpView().hideLoading()));
  }
}
