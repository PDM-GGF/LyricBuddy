package com.progettopdm.lyricbuddy.model;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity(tableName = "tracks", ignoredColumns = {"album", "artists"})

public class Track implements Parcelable {

    @SerializedName("id")
    @PrimaryKey
    @NotNull
    String trackId;
    String name;
    int duration_ms;
    String lyrics;
    String albumId;
    String popularity;
    Album album;
    List<Artist> artists;

    public Track(){}

    public Track(String trackId, String name, int duration_ms, String lyrics) {
        this.trackId = trackId;
        this.name = name;
        this.duration_ms = duration_ms;
        this.lyrics = lyrics;
    }

    public Track(Parcel in) {
        trackId = in.readString();
        name = in.readString();
        duration_ms = in.readInt();
        lyrics = in.readString();
    }

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration_ms() {
        return duration_ms;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public Album getAlbum() {
        return album;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setDuration_ms(int duration_ms) {
        this.duration_ms = duration_ms;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(trackId);
        dest.writeString(name);
        dest.writeInt(duration_ms);
        dest.writeString(lyrics);
    }
}
