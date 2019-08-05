package com.experiment.infinitemovie.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.experiment.infinitemovie.R;
import com.experiment.infinitemovie.data.network.model.MovieObject;
import com.experiment.infinitemovie.ui.base.BaseActivity;
import com.experiment.infinitemovie.ui.detail.DetailFragment;
import com.experiment.infinitemovie.ui.latest.RecentFragment;
import com.experiment.infinitemovie.ui.latest.RecentFragment.CallBack;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainMvpView, CallBack {

  @Inject
  MainMvpPresenter<MainMvpView> mPresenter;

  public static Intent getStartIntent(Context context) {
    Intent intent = new Intent(context, MainActivity.class);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getActivityComponent().inject(this);

    setUnBinder(ButterKnife.bind(this));

    mPresenter.onAttach(this);

    setUp();

    openRecentFragment();
  }

  private void openRecentFragment() {
    RecentFragment topFragment = RecentFragment.newInstance();
    getSupportFragmentManager()
        .beginTransaction().addToBackStack(RecentFragment.TAG)
        .replace(R.id.content_frame, topFragment,
            RecentFragment.TAG)
        .commit();

  }

  private void openDetailFragment(MovieObject movieObject) {
    DetailFragment topFragment = DetailFragment.newInstance(movieObject);
    getSupportFragmentManager()
        .beginTransaction().addToBackStack(DetailFragment.TAG)
        .add(R.id.content_frame, topFragment,
            DetailFragment.TAG)
        .commit();

  }


  @Override
  protected void setUp() {
    openRecentFragment();
  }

  @Override
  public void detailClick(MovieObject movieObject) {
    openDetailFragment(movieObject);
  }
}
