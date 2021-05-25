package com.progettopdm.lyricbuddy.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Track implements Parcelable {

    int trackId;
    String name;
    Uri image_url;
    String lyrics;
    int year;

    public Track(int trackId, String name, Uri image_url, String lyrics, int year) {
        this.trackId = trackId;
        this.name = name;
        this.image_url = image_url;
        this.lyrics = lyrics;
        this.year = year;
    }

    public Track(Parcel in){

    }

    public int getTrackId() {
        return trackId;
    }

    public String getName() {
        return name;
    }

    public Uri getImage_url() {
        return image_url;
    }

    public String getLyrics() {
        return lyrics;
    }

    public int getYear() {
        return year;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage_url(Uri image_url) {
        this.image_url = image_url;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", name='" + name + '\'' +
                ", lyrics='" + lyrics + '\'' +
                ", year=" + year +
                '}';
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

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
}
