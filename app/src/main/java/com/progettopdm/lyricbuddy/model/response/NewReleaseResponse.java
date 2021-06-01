package com.progettopdm.lyricbuddy.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.progettopdm.lyricbuddy.model.Album;
import com.progettopdm.lyricbuddy.model.response.wrappers.AlbumWrapper;


import java.util.List;

public class NewReleaseResponse implements Parcelable {

    @SerializedName(value = "albums")
    private AlbumWrapper albumWrapper;

    public NewReleaseResponse(AlbumWrapper albumWrapper) {
        this.albumWrapper = albumWrapper;
    }

    public AlbumWrapper getAlbumWrapper() {
        return albumWrapper;
    }

    public void setAlbumWrapper(AlbumWrapper albumWrapper) {
        this.albumWrapper = albumWrapper;
    }

    protected NewReleaseResponse(Parcel in) {
        albumWrapper = in.readParcelable(AlbumWrapper.class.getClassLoader());
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(albumWrapper, flags);
    }
}
