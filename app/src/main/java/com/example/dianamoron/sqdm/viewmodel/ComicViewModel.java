package com.example.dianamoron.sqdm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.dianamoron.sqdm.service.api.ComicRepository;
import com.example.dianamoron.sqdm.service.model.Comic;

public class ComicViewModel extends AndroidViewModel {

    private LiveData<Comic> comic;
    private ComicRepository comicRepository;

    public ComicViewModel(@NonNull Application application) {
        super(application);
        comicRepository = new ComicRepository(application);
    }

    public void init(int comicID){
        if(comic != null){
            return;
        }
        //comic = comicRepository.getComicDetails(comicID);
        comic = comicRepository.getComic(comicID);
    }

    public LiveData<Comic> getComicDetails() {
        return comic;
    }


}
