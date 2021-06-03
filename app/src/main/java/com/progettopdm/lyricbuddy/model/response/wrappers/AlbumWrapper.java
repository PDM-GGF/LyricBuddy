package com.progettopdm.lyricbuddy.model.response.wrappers;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.progettopdm.lyricbuddy.model.Album;

import java.util.List;

public class AlbumWrapper implements Parcelable {

    @SerializedName(value = "items")
    private List<Album> albumList;

    public AlbumWrapper(List<Album> albumList) {
        this.albumList = albumList;
    }

    public static final Creator<AlbumWrapper> CREATOR = new Creator<AlbumWrapper>() {
        @Override
        public AlbumWrapper createFromParcel(Parcel in) {
            return new AlbumWrapper(in);
        }

        @Override
        public AlbumWrapper[] newArray(int size) {
            return new AlbumWrapper[size];
        }
    };

    public void AlbumWrapper(Parcel source) {
        this.albumList = source.createTypedArrayList(Album.CREATOR);
    }

    protected AlbumWrapper(Parcel in) {
        this.albumList = in.createTypedArrayList(Album.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(albumList);
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    @Override
    public String toString() {
        return "AlbumWrapper{" +
                ", albumList=" + albumList +
                '}';
    }
}
