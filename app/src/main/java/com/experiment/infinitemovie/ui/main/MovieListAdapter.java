package com.experiment.infinitemovie.ui.main;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.experiment.infinitemovie.R;
import com.experiment.infinitemovie.data.network.ApiEndPoint;
import com.experiment.infinitemovie.data.network.model.MovieObject;
import com.experiment.infinitemovie.ui.base.BaseViewHolder;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<BaseViewHolder> implements Filterable {


  private List<MovieObject> movies;

  private List<MovieObject> moviesFiltered;
  private Callback mCallback;
  private Context mContext;

  public static final int VIEW_TYPE_EMPTY = 0;
  public static final int VIEW_TYPE_NORMAL = 1;

  public MovieListAdapter(List<MovieObject> movies, Context context) {
    this.movies = movies;
    this.mContext = context;
    this.moviesFiltered = movies;
  }

  public void setCallback(Callback callback) {
    mCallback = callback;
  }

  @Override
  public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    switch (viewType) {
      case VIEW_TYPE_NORMAL:
        return new MovieListAdapter.ViewHolder(
            LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.movie_item_view, parent, false));
      case VIEW_TYPE_EMPTY:
      default:
        return new MovieListAdapter.EmptyViewHolder(
            LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.movie_empty_view, parent, false));
    }
  }

  @Override
  public void onBindViewHolder(BaseViewHolder holder, int position) {
    holder.onBind(position);
  }

  @Override
  public int getItemCount() {
    if (moviesFiltered != null && moviesFiltered.size() > 1) {
      return moviesFiltered.size();
    } else {
      return 1;
    }
  }

  @Override
  public int getItemViewType(int position) {
    if (moviesFiltered != null && moviesFiltered.size() > 0) {
      return VIEW_TYPE_NORMAL;
    } else {
      return VIEW_TYPE_EMPTY;
    }
  }

  public void addItems(List<MovieObject> listObjects) {

    moviesFiltered.addAll(listObjects);
    cacheImages(listObjects);
    notifyDataSetChanged();
  }

  public void insertItems(List<MovieObject> listObjects) {

    moviesFiltered.addAll(listObjects);
    cacheImages(listObjects);
    notifyItemInserted(getItemCount() - 1);
  }

  public class ViewHolder extends BaseViewHolder {

    @BindView(R.id.movie_item_image)
    ImageView itemImage;

    @BindView(R.id.movie_item_overview)
    TextView overView;

    @BindView(R.id.movie_item_title)
    TextView title;

    @BindView(R.id.movie_item_release_date)
    TextView releaseDate;

    @BindView(R.id.movie_item_vote_average)
    TextView voteAverage;


    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    @Override
    protected void clear() {

    }

    public void onBind(final int position) {
      super.onBind(position);

      MovieObject movieObject = moviesFiltered.get(position);
      title.setText(movieObject.getTitle());
      overView.setText(movieObject.getOverview());
      voteAverage.setText(
          "Voted " + Double.toString(movieObject.getVoteAverage()) + " points by " + movieObject
              .getVoteCount() + " users");
      releaseDate.setText(movieObject.getReleaseDate());

      GradientDrawable gradientDrawable = new GradientDrawable();
      gradientDrawable.setShape(GradientDrawable.RECTANGLE);
      gradientDrawable.setColor(mContext.getResources().getColor(R.color.dark_green,null));
      Picasso.get().load(ApiEndPoint.IMAGE_BASE + movieObject.getPosterPath())
          .networkPolicy(NetworkPolicy.OFFLINE).placeholder(gradientDrawable)
          .into(itemImage);

    }
  }

  public class EmptyViewHolder extends BaseViewHolder {

    public EmptyViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    @Override
    protected void clear() {

    }
  }

  private void cacheImages(List<MovieObject> listObjects) {
    for (MovieObject movieObject : listObjects) {
      Picasso.get().load(ApiEndPoint.IMAGE_BASE + movieObject.getPosterPath()).fetch();
    }
  }

  public interface Callback {

    void onLoadMore();
  }

  @Override
  public Filter getFilter() {
    return new Filter() {
      @Override
      protected FilterResults performFiltering(CharSequence charSequence) {
        String charString = charSequence.toString();
        if (charString.isEmpty()) {
          moviesFiltered = movies;
        } else {
          List<MovieObject> filteredList = new ArrayList<>();
          for (MovieObject row : movies) {
            if (row.getTitle().toLowerCase().startsWith(charString.toLowerCase())) {
              filteredList.add(row);
            }
          }
          moviesFiltered = filteredList;
        }
        FilterResults filterResults = new FilterResults();
        filterResults.values = moviesFiltered;
        return filterResults;
      }

      @Override
      protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        moviesFiltered = (ArrayList<MovieObject>) filterResults.values;
        notifyDataSetChanged();
      }
    };
  }

}
