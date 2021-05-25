package com.progettopdm.lyricbuddy.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.progettopdm.lyricbuddy.model.Album;


import java.util.List;

public class NewReleaseResponse implements Parcelable {

    @SerializedName(value = "items")
    private List<Album> albumList;

    public NewReleaseResponse(List<Album> albumList) {
        this.albumList = albumList;
        Log.d("Lista album:", "CREATA");
    }

    public void readFromParcel(Parcel source) {
        this.albumList = source.createTypedArrayList(Album.CREATOR);
    }

    protected NewReleaseResponse(Parcel in) {
        this.albumList = in.createTypedArrayList(Album.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.albumList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewReleaseResponse> CREATOR = new Creator<NewReleaseResponse>() {
        @Override
        public NewReleaseResponse createFromParcel(Parcel in) {
            return new NewReleaseResponse(in);
        }

        @Override
        public NewReleaseResponse[] newArray(int size) {
            return new NewReleaseResponse[size];
        }
    };

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    @Override
    public String toString() {
        return "NewReleaseResponse{" +
                ", albumList=" + albumList +
                '}';
    }

}
