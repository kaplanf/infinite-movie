package com.experiment.infinitemovie.data.network;

import com.experiment.infinitemovie.data.network.model.MovieResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiHelper {

    ApiHeader getApiHeader();

    @GET(ApiEndPoint.UPCOMING)
    Observable<MovieResponse> getUpcoming(@Query("api_key") String apikey,@Query("page") String page);
}
