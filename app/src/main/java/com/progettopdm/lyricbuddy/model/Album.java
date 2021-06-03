package com.progettopdm.lyricbuddy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Album extends TrackContainer implements Parcelable {

    @SerializedName("id")
    String albumId;
    @SerializedName("name")
    String name;
    @SerializedName("images")
    List<GenericImage> genericImageList;
    List<Track> trackList;
    List<Artist> artists;


    public Album(String name, List<GenericImage> genericImageList) {
        this.name = name;
        this.genericImageList = genericImageList;
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
        String artistList = "";
        for(Artist a : artists){
            if(a.equals(artists.get(0)))
                artistList += a.getName();
            else
                artistList += ", " + a.getName();
        }
        return artistList;
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

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    @Override
    public String getId() {
        return albumId;
    }

    public String getName() {
        return name;
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


