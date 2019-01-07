package com.example.dianamoron.sqdm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.dianamoron.sqdm.service.api.ComicApiService;
import com.example.dianamoron.sqdm.service.api.ComicRepository;
import com.example.dianamoron.sqdm.service.database.ComicDao;
import com.example.dianamoron.sqdm.service.model.Comic;
import com.example.dianamoron.sqdm.service.model.ComicResponse;

import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class ComicListViewModel extends AndroidViewModel {

    private LiveData<List<Comic>> heroList;
    private ComicRepository comicRepository;


    public ComicListViewModel(@NonNull Application application) {
        super(application);
        comicRepository = new ComicRepository(application);
    }

    public void insertComics(){
        comicRepository.insertComics();
        heroList = comicRepository.getComicsList();
    }

    public void deleteAllComics() {
        comicRepository.deleteAllComics();
    }

    //we will call this method to get the data
    public LiveData<List<Comic>> getComics() {
        return heroList;
    }
}
