package com.experiment.infinitemovie.ui.latest;

import com.experiment.infinitemovie.ui.base.MvpPresenter;

public interface RecentMvpPresenter<V extends RecentMvpView> extends MvpPresenter<V>  {
  void getLatestMovies(int page);
}
