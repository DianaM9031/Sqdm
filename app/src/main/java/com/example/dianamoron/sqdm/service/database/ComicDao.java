package com.example.dianamoron.sqdm.service.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dianamoron.sqdm.service.model.Comic;

import java.util.List;

@Dao
public interface ComicDao {

    @Insert
    void insert(Comic comic);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllComics(Comic... comics);

    @Update
    void update(Comic comic);

    @Delete
    void delete(Comic comic);

    @Query("DELETE FROM comic_table")
    void deleteAll();

    @Query("SELECT * FROM comic_table")
    LiveData<List<Comic>> getAllComics();

    @Query("SELECT * FROM comic_table WHERE id = :id")
    LiveData<Comic> getComicDetails(int id);
}
