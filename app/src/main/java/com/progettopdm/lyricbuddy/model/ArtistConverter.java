package com.progettopdm.lyricbuddy.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.progettopdm.lyricbuddy.model.Artist;

import java.lang.reflect.Type;
import java.util.List;

// THIS CLASS CONVERTS ARTIST LIST TO A STRING
public class ArtistConverter {

    @TypeConverter
    public static List<Artist> fromString(String value) {
        Type listType = new TypeToken<List<Artist>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
    @TypeConverter
    public static String fromList(List<Artist> list) {

        StringBuilder artistList = new StringBuilder();
        for (Artist a : list) {
            if (a.equals(list.get(0)))
                artistList.append(a.getName());
            else
                artistList.append(", ").append(a.getName());
        }
        return artistList.toString();
    }

}
