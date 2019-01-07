package com.example.dianamoron.sqdm.service.model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.dianamoron.sqdm.service.api.ComicApiService;
import com.example.dianamoron.sqdm.service.api.ComicRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class ComicResponse {

    private ArrayList<Comic> results;


    public ArrayList<Comic> getResults() {
        return results;
    }

    public void setResults(ArrayList<Comic> results) {
        this.results = results;
    }



    /*public MutableLiveData<ComicResponse> getComics(){
        final MutableLiveData<ComicResponse> comicsList = new MutableLiveData<>();

        Call<ComicResponse> comicResponseCall = ComicRepository.getInstance().getComicList(ComicApiService.API_KEY,"json");

        comicResponseCall.enqueue(new Callback<ComicResponse>() {
            @Override
            public void onResponse(Call<ComicResponse> call, Response<ComicResponse> response) {
                if(response.isSuccessful()){

                    comicsList.setValue(response.body());

                } else {
                    Log.e(TAG,"RESPUESTA DEL API "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ComicResponse> call, Throwable t) {
                Log.e(TAG,"Error "+t.getMessage());
            }
        });

        return comicsList;

    }*/


}
