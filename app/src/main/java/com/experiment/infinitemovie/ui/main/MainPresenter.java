package com.experiment.infinitemovie.ui.main;

import com.experiment.infinitemovie.data.DataManager;
import com.experiment.infinitemovie.ui.base.BasePresenter;
import com.experiment.infinitemovie.util.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
    implements MainMvpPresenter<V> {

  private static final String TAG = "MainPresenter";

  @Inject
  public MainPresenter(DataManager dataManager,
                       SchedulerProvider schedulerProvider,
                       CompositeDisposable compositeDisposable) {
    super(dataManager, schedulerProvider, compositeDisposable);
  }
}
