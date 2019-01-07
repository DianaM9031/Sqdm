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

}
