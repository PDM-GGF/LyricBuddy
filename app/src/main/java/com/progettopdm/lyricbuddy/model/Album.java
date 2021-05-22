package com.progettopdm.lyricbuddy.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Album implements Parcelable {

    int albumId;
    String title;
    int artistId;
    ArrayList<Track> trackList;
    int year;

    public Album(int albumId, String title, int artistId, ArrayList<Track> trackList, int year) {
        this.albumId = albumId;
        this.title = title;
        this.artistId = artistId;
        this.trackList = trackList;
        this.year = year;
    }

    public Album(){
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtist(int artistId) {
        this.artistId = artistId;
    }

    public ArrayList<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(ArrayList<Track> trackList) {
        this.trackList = trackList;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    protected Album(Parcel in) {
        albumId = in.readInt();
        title = in.readString();
        artistId = in.readInt();
        trackList = in.createTypedArrayList(Track.CREATOR);
        year = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(albumId);
        dest.writeString(title);
        dest.writeInt(artistId);
        dest.writeTypedList(trackList);
        dest.writeInt(year);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
