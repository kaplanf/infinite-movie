package com.experiment.infinitemovie.ui.latest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.experiment.infinitemovie.R;
import com.experiment.infinitemovie.data.network.model.MovieObject;
import com.experiment.infinitemovie.di.component.ActivityComponent;
import com.experiment.infinitemovie.ui.base.BaseFragment;
import com.experiment.infinitemovie.ui.main.MovieListAdapter;
import com.experiment.infinitemovie.util.NetworkUtils;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class RecentFragment extends BaseFragment implements RecentMvpView,
    MovieListAdapter.Callback {

  public static final String TAG = "RecentFragment";

  private int lastVisibleItem, totalItemCount;
  private int visibleThreshold = 20;

  @Inject
  RecentMvpPresenter<RecentMvpView> mPresenter;

  @Inject
  LinearLayoutManager mLayoutManager;

  @Inject
  MovieListAdapter adapter;

  @BindView(R.id.movie_recycler_view)
  RecyclerView movieRecyclerview;

  private ArrayList<MovieObject> movies;

  private int page = 1;

  SearchView.SearchAutoComplete searchAutoComplete;
  SearchView searchView;

  public static RecentFragment newInstance() {
    Bundle args = new Bundle();
    RecentFragment fragment = new RecentFragment();
    return fragment;
  }

  public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
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
  protected void setUp(View view) {
    if (NetworkUtils.isNetworkConnected(getActivity())) {
      mPresenter.getLatestMovies(page);
    }
  }

  @Override
  public void loadMovies(List<MovieObject> movieObjects) {
    movies = (ArrayList<MovieObject>) movieObjects;
    populateAutoComplete();

    adapter.setCallback(this);
    adapter.addItems(movies);
    mLayoutManager.setOrientation(RecyclerView.VERTICAL);
    movieRecyclerview.setLayoutManager(mLayoutManager);
    movieRecyclerview.setAdapter(adapter);

    movieRecyclerview
        .addOnScrollListener(new OnScrollListener() {
          @Override
          public void onScrolled(RecyclerView recyclerView,
              int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                .getLayoutManager();
            totalItemCount = linearLayoutManager.getItemCount();
            lastVisibleItem = linearLayoutManager
                .findLastVisibleItemPosition();
            if (totalItemCount - 1 <= (lastVisibleItem) && totalItemCount >= visibleThreshold) {
              onLoadMore();
            }
          }
        });
  }

  @Override
  public void addMovies(List<MovieObject> movieObjects) {
    movies.addAll(movieObjects);
    populateAutoComplete();
    adapter.insertItems(movieObjects);
  }

  @Override
  public void onLoadMore() {
    page++;
    mPresenter.getLatestMovies(page);
  }

  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    menu.clear();
    inflater.inflate(R.menu.menu_main, menu);
    MenuItem item = menu.findItem(R.id.action_search);
    searchView = (SearchView) item.getActionView();
    item.setShowAsAction(
        MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
    item.setActionView(searchView);
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        adapter.getFilter().filter(query);
        return false;
      }

      @Override
      public boolean onQueryTextChange(String query) {
        adapter.getFilter().filter(query);
        return false;
      }
    });
  }

  public void populateAutoComplete() {
    searchAutoComplete = searchView
        .findViewById(com.google.android.material.R.id.search_src_text);
    String[] titles = movies.stream().map(MovieObject::getTitle)
        .toArray(String[]::new);
    ArrayAdapter<String> titleAdapter = new ArrayAdapter<>(getActivity(),
        android.R.layout.simple_dropdown_item_1line, titles);

    searchAutoComplete.setDropDownBackgroundResource(android.R.color.white);
    searchAutoComplete.setAdapter(titleAdapter);
    searchAutoComplete.setOnItemClickListener((adapterView, view, i, l) -> {
      searchAutoComplete.dismissDropDown();
      searchView.setQuery(adapterView.getItemAtPosition(i).toString(),true);
    });

  }
}
