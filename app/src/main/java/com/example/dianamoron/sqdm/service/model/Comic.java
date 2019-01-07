package com.example.dianamoron.sqdm.service.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.os.Parcelable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dianamoron.sqdm.service.database.Converters;

@Entity(tableName = "comic_table")
public class Comic {

    @PrimaryKey
    private int id;
    private String name;
    private String description;
    @TypeConverters({Converters.class})
    private ComicImage image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ComicImage getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(ComicImage image) {
        this.image = image;
    }

}
