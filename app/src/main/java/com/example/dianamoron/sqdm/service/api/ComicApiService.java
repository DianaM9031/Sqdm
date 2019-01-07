package com.example.dianamoron.sqdm.service.api;


import com.example.dianamoron.sqdm.service.model.Comic;
import com.example.dianamoron.sqdm.service.model.ComicResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ComicApiService {

    public static final String BASE_URL = "https://comicvine.gamespot.com/api/";
    public static final String API_KEY = "0a33d7004cce306f28cbba941b1b754e19524b00";

    @GET("movies/")
    Call<ComicResponse> getComicList(@Query("api_key") String api_key,
                                        @Query("format") String format);

    @GET("movies/")
    Call<ComicResponse> getComicDetails(@Query("api_key") String api_key,
                                     @Query("format") String format,
                                     @Query("filter") String filter);
}
