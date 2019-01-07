package com.example.dianamoron.sqdm.service.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.dianamoron.sqdm.service.model.Comic;
import com.example.dianamoron.sqdm.service.model.User;

@Database(entities = Comic.class, version = 5)
public abstract class ComicDatabase extends RoomDatabase {

    private static ComicDatabase instance;
    public abstract ComicDao comicDao();


    public static synchronized ComicDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ComicDatabase.class, "comic_database")
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }

}
