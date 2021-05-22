package com.progettopdm.lyricbuddy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Track implements Parcelable {

    int trackId;
    String title;
    String image_url;
    String lyrics;
    int year;

    public Track(int trackId, String title, String image_url, String lyrics, int year) {
        this.trackId = trackId;
        this.title = title;
        this.image_url = image_url;
        this.lyrics = lyrics;
        this.year = year;
    }

    public Track(){

    }

    public int getTrackId() {
        return trackId;
    }

    public String getTitle() {
        return title;
    }

    public String getImage_url() {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage_url(String image_url) {
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
                ", title='" + title + '\'' +
                ", lyrics='" + lyrics + '\'' +
                ", year=" + year +
                '}';
    }

    protected Track(Parcel in) {
        trackId = in.readInt();
        title = in.readString();
        image_url = in.readString();
        lyrics = in.readString();
        year = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(trackId);
        dest.writeString(title);
        dest.writeString(image_url);
        dest.writeString(lyrics);
        dest.writeInt(year);
    }

    @Override
    public int describeContents() {
        return 0;
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
