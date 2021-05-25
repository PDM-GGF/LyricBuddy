package com.progettopdm.lyricbuddy.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.progettopdm.lyricbuddy.model.response.wrappers.PlaylistWrapper;

public class FeaturedResponse implements Parcelable {

    String message;
    @SerializedName("playlists")
    PlaylistWrapper playlistWrapper;

    public FeaturedResponse(String message, PlaylistWrapper playlistWrapper) {
        this.message = message;
        this.playlistWrapper = playlistWrapper;
    }

    protected FeaturedResponse(Parcel in) {
        message = in.readString();
    }

    public static final Creator<FeaturedResponse> CREATOR = new Creator<FeaturedResponse>() {
        @Override
        public FeaturedResponse createFromParcel(Parcel in) {
            return new FeaturedResponse(in);
        }

        @Override
        public FeaturedResponse[] newArray(int size) {
            return new FeaturedResponse[size];
        }
    };

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PlaylistWrapper getPlaylistWrapper() {
        return playlistWrapper;
    }

    public void setPlaylistWrapper(PlaylistWrapper playlistWrapper) {
        this.playlistWrapper = playlistWrapper;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
    }
}
