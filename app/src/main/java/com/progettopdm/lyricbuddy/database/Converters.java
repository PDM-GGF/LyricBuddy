package com.progettopdm.lyricbuddy.database;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.progettopdm.lyricbuddy.model.Album;
import com.progettopdm.lyricbuddy.model.Artist;
import com.progettopdm.lyricbuddy.model.GenericImage;
import com.progettopdm.lyricbuddy.model.Track;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Modifier.TRANSIENT;

@ProvidedTypeConverter
public class Converters {

   @TypeConverter
    public static Album stringToAlbum (String album) {
       Album a;
       a = new Gson().fromJson(album, Album.class);
       return a;
    }

    @TypeConverter
    public static String albumToString (Album album) {
        Type albumType = new TypeToken<Album>() {}.getType();
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .excludeFieldsWithModifiers(TRANSIENT) // STATIC|TRANSIENT in the default configuration
                .create();
        String json = gson.toJson(album, albumType);
        return json;
    }

    @TypeConverter
    public static List<Artist> stringToArtist (String artist) {
        Type listArtist = new TypeToken<ArrayList<Artist>>() {}.getType();
        return new Gson().fromJson(artist, listArtist);
    }

    @TypeConverter
    public static String artistToString (List<Artist> artist) {
        Gson gson = new Gson();
        String json = gson.toJson(artist);
        return json;
    }


    @TypeConverter
    public static List<GenericImage> stringToImage (String image) {
        Type img = new TypeToken<ArrayList<GenericImage>>() {}.getType();
        return new Gson().fromJson(image, img);
    }

    @TypeConverter
    public static String imageToString (List<GenericImage> img) {
        Gson gson = new Gson();
        String json = gson.toJson(img);
        return json;
    }


    @TypeConverter
    public static List<Track> stringToTrack (String tracklist) {
        Type tL = new TypeToken<ArrayList<Track>>() {}.getType();
        return new Gson().fromJson(tracklist, tL);
    }

    @TypeConverter
    public static String tracklistToString (List<Track> trackList) {
        Gson gson = new Gson();
        String json = gson.toJson(trackList);
        return json;
    }


}
