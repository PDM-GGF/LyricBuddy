package com.progettopdm.lyricbuddy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Playlist extends TrackContainer implements Parcelable {

    int playlistId;
    String name;
    String description;
    @SerializedName("images")
    List<GenericImage> playlistImgList;


    public Playlist(int playlistId, String name, String image_url, String description, List<GenericImage> playlistImageList) {
        this.playlistId = playlistId;
        this.name = name;
        this.playlistImgList = playlistImageList;
        this.description = description;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getName() {
        return name;
    }

    @Override
    public List<GenericImage> getImgList() {
        return playlistImgList;
    }

    @Override
    public List<Track> getTrackList() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GenericImage> getPlaylistImgList() {
        return playlistImgList;
    }

    public void setPlaylistImgList(List<GenericImage> playlistImgList) {
        this.playlistImgList = playlistImgList;
    }

    protected Playlist(Parcel in) {
        playlistId = in.readInt();
        name = in.readString();
        description = in.readString();
        playlistImgList = in.createTypedArrayList(GenericImage.CREATOR);
    }

    public static final Creator<Playlist> CREATOR = new Creator<Playlist>() {
        @Override
        public Playlist createFromParcel(Parcel in) {
            return new Playlist(in);
        }

        @Override
        public Playlist[] newArray(int size) {
            return new Playlist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(playlistId);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeTypedList(playlistImgList);
    }
}