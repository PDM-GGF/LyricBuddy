package com.progettopdm.lyricbuddy.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Album implements Parcelable {

    @SerializedName("name")
    String title;
    @SerializedName("images")
    List<AlbumImg> albumImgList;



    public Album(String title, List<AlbumImg> albumImgList) {
        this.title = title;
        this.albumImgList = albumImgList;
    }

    public List<AlbumImg> getAlbumImgList() {
        return albumImgList;
    }

    public void setAlbumImgList(List<AlbumImg> albumImgList) {
        this.albumImgList = albumImgList;
    }

    protected Album(Parcel in) {
        title = in.readString();
        albumImgList = in.createTypedArrayList(AlbumImg.CREATOR);
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

    public String getTitle() {
        return title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeTypedList(albumImgList);
    }
}


