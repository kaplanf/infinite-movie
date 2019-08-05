package com.experiment.infinitemovie.ui.base;

import com.experiment.infinitemovie.data.DataManager;
import com.experiment.infinitemovie.util.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

  private static final String TAG = "BasePresenter";

  private final DataManager mDataManager;
  private final SchedulerProvider mSchedulerProvider;
  private final CompositeDisposable mCompositeDisposable;

  private V mMvpView;

  @Inject
  public BasePresenter(DataManager dataManager,
                       SchedulerProvider schedulerProvider,
                       CompositeDisposable compositeDisposable) {
    this.mDataManager = dataManager;
    this.mSchedulerProvider = schedulerProvider;
    this.mCompositeDisposable = compositeDisposable;
  }

  @Override
  public void onAttach(V mvpView) {
    mMvpView = mvpView;
  }

  @Override
  public void onDetach() {
    mCompositeDisposable.dispose();
    mMvpView = null;
  }

  public boolean isViewAttached() {
    return mMvpView != null;
  }

  public V getMvpView() {
    return mMvpView;
  }

  public DataManager getDataManager() {
    return mDataManager;
  }

  public SchedulerProvider getSchedulerProvider() {
    return mSchedulerProvider;
  }

  public CompositeDisposable getCompositeDisposable() {
    return mCompositeDisposable;
  }
}
