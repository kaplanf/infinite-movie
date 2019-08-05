package com.experiment.infinitemovie.data;


import com.experiment.infinitemovie.data.network.ApiHelper;
import com.experiment.infinitemovie.data.prefs.PreferencesHelper;

public interface DataManager extends PreferencesHelper, ApiHelper {

    void updateApiHeader(String accessToken);
}
