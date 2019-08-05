package com.experiment.infinitemovie.ui.detail;

import com.experiment.infinitemovie.data.DataManager;
import com.experiment.infinitemovie.ui.base.BasePresenter;
import com.experiment.infinitemovie.util.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;

public class DetailPresenter<V extends DetailMvpView> extends BasePresenter<V> implements DetailMvpPresenter<V> {

  private static final String TAG = "DetailPresenter";

  @Inject
  public DetailPresenter(DataManager dataManager,
      SchedulerProvider schedulerProvider,
      CompositeDisposable compositeDisposable) {
    super(dataManager, schedulerProvider, compositeDisposable);
  }
}
