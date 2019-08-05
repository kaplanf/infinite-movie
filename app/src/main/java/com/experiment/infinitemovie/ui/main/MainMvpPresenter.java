package com.experiment.infinitemovie.ui.main;


import com.experiment.infinitemovie.di.PerActivity;
import com.experiment.infinitemovie.ui.base.MvpPresenter;

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
}
