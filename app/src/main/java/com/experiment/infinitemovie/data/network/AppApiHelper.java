package com.experiment.infinitemovie.data.network;


import com.experiment.infinitemovie.data.network.model.MovieResponse;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class AppApiHelper implements ApiHelper {

  private ApiHeader mApiHeader;

  @Inject
  public AppApiHelper(ApiHeader apiHeader) {
    mApiHeader = apiHeader;
  }

  @Override
  public ApiHeader getApiHeader() {
    return mApiHeader;
  }

  @Override
  public Observable<MovieResponse> getUpcoming(String apikey, String page) {
    Retrofit retrofit = new Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(ApiEndPoint.BASE_URL)
        .build();

    ApiHelper apiHelper = retrofit.create(ApiHelper.class);

    return apiHelper.getUpcoming(apikey,page);
  }


}

