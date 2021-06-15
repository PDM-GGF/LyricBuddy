package com.progettopdm.lyricbuddy.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.progettopdm.lyricbuddy.database.Converters;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity(tableName = "tracks",ignoredColumns = "album")

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

    //@TypeConverters(Converters.class)
    Album album;

    @TypeConverters(Converters.class)
    public List<Artist> artists;

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

    public List<Artist> getArtist() {
        return this.artists;
    }

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
