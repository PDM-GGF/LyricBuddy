package com.progettopdm.lyricbuddy.model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.bumptech.glide.RequestBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.net.URL;

public class AlbumImg implements Parcelable {

    @SerializedName("url")
    URL imgUrl;
    RequestBuilder<Drawable> albumImg;

    public AlbumImg(URL imgUrl) throws IOException {
        this.imgUrl = imgUrl;
    }

    protected AlbumImg(Parcel in) {
    }

    public static final Creator<AlbumImg> CREATOR = new Creator<AlbumImg>() {
        @Override
        public AlbumImg createFromParcel(Parcel in) {
            return new AlbumImg(in);
        }

        @Override
        public AlbumImg[] newArray(int size) {
            return new AlbumImg[size];
        }
    };

    public RequestBuilder<Drawable> getAlbumImg() {
        return albumImg;
    }

    public void setAlbumImg(RequestBuilder<Drawable> albumImg) {
        this.albumImg = albumImg;
    }


    @Override
    public String toString() {
        return "AlbumImg{" +
                "imgUrl=" + imgUrl +
                '}';
    }

    public URL getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(URL imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

}
