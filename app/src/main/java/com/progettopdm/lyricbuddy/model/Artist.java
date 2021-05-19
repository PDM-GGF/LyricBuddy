package com.progettopdm.lyricbuddy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Artist implements Parcelable {

    int artistId;
    String name;
    String country;
    String age;

    public Artist(int artistId, String name, String country, String age) {
        this.artistId = artistId;
        this.name = name;
        this.country = country;
        this.age = age;
    }

    public Artist() {
    }

    public int getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getAge() {
        return age;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(artistId);
        dest.writeString(name);
        dest.writeString(country);
        dest.writeString(age);
    }

    protected Artist(Parcel in) {
        artistId = in.readInt();
        name = in.readString();
        country = in.readString();
        age = in.readString();
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
