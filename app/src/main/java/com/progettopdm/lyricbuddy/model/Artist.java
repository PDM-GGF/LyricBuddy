package com.progettopdm.lyricbuddy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Artist implements Parcelable {

    @SerializedName("id")
    String artistId;
    String name;

    public Artist(String artistId, String name, String country, String age) {
        this.artistId = artistId;
        this.name = name;
    }

    public Artist() {
    }

    public String getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }


    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(artistId);
        dest.writeString(name);
    }

    protected Artist(Parcel in) {
        artistId = in.readString();
        name = in.readString();
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };
}
