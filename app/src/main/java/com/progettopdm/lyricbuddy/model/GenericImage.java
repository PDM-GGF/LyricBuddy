package com.progettopdm.lyricbuddy.model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.bumptech.glide.RequestBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.net.URL;

public class GenericImage implements Parcelable {

    @SerializedName("url")
    URL imgUrl;
    RequestBuilder<Drawable> img;

    public GenericImage(URL imgUrl) throws IOException {
        this.imgUrl = imgUrl;
    }

    protected GenericImage(Parcel in) {
    }

    public static final Creator<GenericImage> CREATOR = new Creator<GenericImage>() {
        @Override
        public GenericImage createFromParcel(Parcel in) {
            return new GenericImage(in);
        }

        @Override
        public GenericImage[] newArray(int size) {
            return new GenericImage[size];
        }
    };

    public RequestBuilder<Drawable> getImg() {
        return img;
    }

    public void setImg(RequestBuilder<Drawable> img) {
        this.img = img;
    }


    @Override
    public String toString() {
        return "img{" +
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
