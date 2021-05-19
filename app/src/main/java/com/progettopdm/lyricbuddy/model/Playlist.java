package com.progettopdm.lyricbuddy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Playlist implements Parcelable {

    int playlistId;
    String name;
    String image_url;
    String description;
    String genre;

    public Playlist(int playlistId, String name, String image_url, String description, String genre) {
        this.playlistId = playlistId;
        this.name = name;
        this.image_url = image_url;
        this.description = description;
        this.genre = genre;
    }

    public Playlist(){

    }

    public int getPlaylistId() {
        return playlistId;
    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistId=" + playlistId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genre=" + genre +
                '}';
    }

    protected Playlist(Parcel in) {
        playlistId = in.readInt();
        name = in.readString();
        image_url = in.readString();
        description = in.readString();
        genre = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(playlistId);
        dest.writeString(name);
        dest.writeString(image_url);
        dest.writeString(description);
        dest.writeString(genre);
    }

    @Override
    public int describeContents() {
        return 0;
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
}