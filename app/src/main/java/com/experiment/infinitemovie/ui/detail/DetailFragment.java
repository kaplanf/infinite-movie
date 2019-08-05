package com.experiment.infinitemovie.ui.detail;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.experiment.infinitemovie.R;
import com.experiment.infinitemovie.data.network.ApiEndPoint;
import com.experiment.infinitemovie.data.network.model.MovieObject;
import com.experiment.infinitemovie.di.component.ActivityComponent;
import com.experiment.infinitemovie.ui.base.BaseFragment;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import javax.inject.Inject;

public class DetailFragment extends BaseFragment implements DetailMvpView {

  public static final String TAG = "DetailFragment";

  @Inject
  DetailMvpPresenter<DetailMvpView> mPresenter;

  @BindView(R.id.detail_image)
  ImageView detailImage;

  @BindView(R.id.detail_title)
  TextView titleText;

  @BindView(R.id.detail_release_date)
  TextView releaseDateText;

  @BindView(R.id.detail_info)
  TextView infoText;

  @BindView(R.id.detail_overview)
  TextView overViewText;

  MovieObject movieObject;

  public static DetailFragment newInstance(MovieObject movieObject) {
    Bundle args = new Bundle();
    args.putSerializable("movieObject", movieObject);
    DetailFragment fragment = new DetailFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public void onAttach(Context context) {
    super.onAttach(context);
    movieObject = (MovieObject) getArguments().getSerializable("movieObject");
  }

  @Override
  protected void setUp(View view) {
    if (movieObject != null) {
      titleText.setText(movieObject.getTitle());
      releaseDateText.setText(movieObject.getReleaseDate());
      infoText.setText(
          "Voted " + Double.toString(movieObject.getVoteAverage()) + " points by " + movieObject
              .getVoteCount() + " users");
      overViewText.setText(movieObject.getOverview());

      GradientDrawable gradientDrawable = new GradientDrawable();
      gradientDrawable.setShape(GradientDrawable.RECTANGLE);
      gradientDrawable.setColor(getActivity().getResources().getColor(R.color.dark_green, null));
      Picasso.get().load(ApiEndPoint.IMAGE_BASE + movieObject.getPosterPath())
          .networkPolicy(NetworkPolicy.OFFLINE).placeholder(gradientDrawable)
          .into(detailImage);
    }
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_detail, container, false);
    ActivityComponent component = getActivityComponent();
    setHasOptionsMenu(true);
    if (component != null) {
      component.inject(this);
      setUnBinder(ButterKnife.bind(this, view));
      mPresenter.onAttach(this);
    }
    return view;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    menu.clear();
  }
}
