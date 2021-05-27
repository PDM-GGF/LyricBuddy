package com.progettopdm.lyricbuddy.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Track implements Parcelable {

    @SerializedName("id")
    String trackId;
    String name;
    int duration_ms;
    String lyrics;

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

    public void setDuration_ms(int duration_ms) {
        this.duration_ms = duration_ms;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
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
