package com.example.dianamoron.sqdm.service.api;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.dianamoron.sqdm.service.database.ComicDao;
import com.example.dianamoron.sqdm.service.database.ComicDatabase;
import com.example.dianamoron.sqdm.service.model.Comic;
import com.example.dianamoron.sqdm.service.model.ComicResponse;
import com.example.dianamoron.sqdm.service.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;


/*
 * Singleton Pattern
 */
public class ComicRepository {

    private static Retrofit retrofit;
    private static ComicApiService instance;
    private static Gson gson;

    LiveData<List<Comic>> comicsList;
    LiveData<Comic> comicDetails;
    private ComicDao comicDao;

    public ComicRepository(Context context){
        Log.e(TAG,"ENTRO EN COMIC REPOSITORY");
        ComicDatabase comicDatabase = ComicDatabase.getInstance(context);
        comicDao = comicDatabase.comicDao();
    }

    public void insertAll(Comic... comics){
        new InsertComicAsyncTask(comicDao).execute(comics);
    }

    public void deleteAllComics(){
        new DeleteAllComicsAsyncTask(comicDao).execute();
    }

    public LiveData<Comic> getComic(int comicID){
        comicDetails = comicDao.getComicDetails(comicID);
        return comicDetails;
    }

    public LiveData<List<Comic>> getComicsList(){
        comicsList = comicDao.getAllComics();
        return comicsList;
    }

    private static class InsertComicAsyncTask extends AsyncTask<Comic, Void, Void> {
        private ComicDao comicDao;

        private InsertComicAsyncTask(ComicDao comicDao){
            this.comicDao = comicDao;
        }

        @Override
        protected Void doInBackground(Comic... comics) {
            comicDao.insertAllComics(comics);
            return null;
        }
    }

    private static class DeleteAllComicsAsyncTask extends AsyncTask<Void,Void,Void> {
        private ComicDao comicDao;

        private DeleteAllComicsAsyncTask(ComicDao comicDao){
            this.comicDao = comicDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            comicDao.deleteAll();
            return null;
        }
    }


    //This method is using Retrofit to get the JSON data from URL
    public static void getInstance() {
        if (instance == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .retryOnConnectionFailure(true)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build();

            gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(ComicApiService.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            instance = retrofit.create(ComicApiService.class);
        }
    }

   /* public MutableLiveData<List<Comic>> getComics(){
        getInstance();
        comicsList = new MutableLiveData<>();

        Call<ComicResponse> comicResponseCall = instance.getComicList(ComicApiService.API_KEY,"json");

        comicResponseCall.enqueue(new Callback<ComicResponse>() {
            @Override
            public void onResponse(Call<ComicResponse> call, Response<ComicResponse> response) {
                if(response.isSuccessful()){
                    deleteAllComics();
                    insertAll(response.body().getResults());
                    //comicsList.setValue(response.body().getResults());
                    //Log.e(TAG,"ESTO ES HEROLIST "+comicsList.getValue().get(0).getName());

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

    public void insertComics(){
        getInstance();
        comicsList = new MutableLiveData<>();

        Call<ComicResponse> comicResponseCall = instance.getComicList(ComicApiService.API_KEY,"json");

        comicResponseCall.enqueue(new Callback<ComicResponse>() {
            @Override
            public void onResponse(Call<ComicResponse> call, Response<ComicResponse> response) {
                if(response.isSuccessful()){
                    deleteAllComics();
                    Comic[]comics = response.body().getResults().toArray(new Comic[response.body().getResults().size()]);
                    insertAll(comics);
                } else {
                    Log.e(TAG,"RESPUESTA DEL API "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ComicResponse> call, Throwable t) {
                Log.e(TAG,"Error "+t.getMessage());
            }
        });



    }

    /*public LiveData<Comic> getComicDetails(int comicID) {
        getInstance();
        comicDetails = new LiveData<>();

        Call<ComicResponse> comicResponseCall = instance.getComicDetails(ComicApiService.API_KEY,"json","id:"+comicID);

        comicResponseCall.enqueue(new Callback<ComicResponse>() {
            @Override
            public void onResponse(Call<ComicResponse> call, Response<ComicResponse> response) {
                if(response.isSuccessful()){
                    comicDetails.setValue(response.body().getResults().get(0));
                } else {
                    Log.e(TAG,"RESPUESTA DEL API "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ComicResponse> call, Throwable t) {
                Log.e(TAG,"Error "+t.getMessage());
            }
        });

        return comicDetails;

    }*/

}
