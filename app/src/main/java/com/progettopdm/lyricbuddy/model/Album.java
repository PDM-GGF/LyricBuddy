package com.progettopdm.lyricbuddy.model;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.database.Converters;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

@Entity(tableName = "albums")
public class Album extends TrackContainer implements Parcelable {

    @Expose
    @SerializedName("id")
    @PrimaryKey
    @NotNull
    String albumId;
    @Expose
    @SerializedName("name")
    String name;
    @SerializedName("images")
    @Expose
    @TypeConverters(Converters.class)
    List<GenericImage> genericImageList;
    @TypeConverters(Converters.class)
    List<Track> trackList;
    @Expose
    @TypeConverters(Converters.class)
    List<Artist> artists;
    @Expose
    String release_date;

    public Album(){
        this.albumId = "";
    }

    public Album(String name, List<GenericImage> genericImageList) {
        this.name = name;
        this.genericImageList = genericImageList;
        this.albumId = "";

    }

    public List<GenericImage> getImgList() {
        return genericImageList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }


    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public String getDescription() {
        StringBuilder artistList = new StringBuilder();
        for (Artist a : artists) {
            if (a.equals(artists.get(0)))
                artistList.append(a.getName());
            else
                artistList.append(", ").append(a.getName());
        }
        return artistList.toString();
    }

    @Override
    public List<Track> getTrackList() {
        return trackList;
    }

    public void setImgList(List<GenericImage> genericImageList) {
        this.genericImageList = genericImageList;
    }

    protected Album(Parcel in) {
        name = in.readString();
        genericImageList = in.createTypedArrayList(GenericImage.CREATOR);
        albumId = "";
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    public void setAlbumId(@NotNull String albumId) {
        this.albumId = Objects.requireNonNull(albumId);
    }

    @Override
    public String getId() {
        return albumId;
    }

    public String getName() {
        return name;
    }


    public String getRelease_date() {
        return release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(genericImageList);
    }
}


