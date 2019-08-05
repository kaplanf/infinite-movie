package com.experiment.infinitemovie.ui.latest;

import com.experiment.infinitemovie.data.network.model.MovieObject;
import com.experiment.infinitemovie.ui.base.MvpView;
import java.util.List;

public interface RecentMvpView extends MvpView {
  void loadMovies(List<MovieObject> movieObjects);

  void addMovies(List<MovieObject> movieObjects);
}
