package com.example.dianamoron.sqdm.service.database;

import android.arch.persistence.room.TypeConverter;

import com.example.dianamoron.sqdm.service.model.ComicImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static ComicImage fromString(String value) {
        Type listType = new TypeToken<ComicImage>() {}.getType();
        return new Gson().fromJson(value, listType);
        // return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static String objectToString(ComicImage list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        return json;
        // return date == null ? null : date.getTime();
    }
}