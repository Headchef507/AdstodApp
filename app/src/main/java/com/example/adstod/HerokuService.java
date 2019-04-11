package com.example.adstod;

import org.json.JSONArray;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HerokuService {
    String BASE_URL = "https://adstodbackend.herokuapp.com/";

    @GET("allquestions")
    Call<JSONArray> hello();
}