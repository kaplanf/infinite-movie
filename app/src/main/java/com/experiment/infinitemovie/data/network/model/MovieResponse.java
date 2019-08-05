package com.experiment.infinitemovie.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieResponse implements Serializable {

  @SerializedName("page")
  private int page;

  @SerializedName("total_results")
  private int totalResults;

  @SerializedName("total_pages")
  private int totalPages;

  @SerializedName("results")
  private ArrayList<MovieObject> results;

  private int category;

  public int getPage() {
    return page;
  }

  public int getTotalResults() {
    return totalResults;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public ArrayList<MovieObject> getResults() {
    return results;
  }

  public void setCategory(int category) {
    this.category = category;
  }

}
