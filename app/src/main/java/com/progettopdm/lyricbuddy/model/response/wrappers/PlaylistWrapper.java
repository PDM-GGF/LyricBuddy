package com.progettopdm.lyricbuddy.model.response.wrappers;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.progettopdm.lyricbuddy.model.Playlist;

import java.util.List;

public class PlaylistWrapper implements Parcelable {

    @SerializedName("items")
    List<Playlist> playlistList;

    public PlaylistWrapper(List<Playlist> playlistList) {
        this.playlistList = playlistList;
    }

    public List<Playlist> getPlaylistList() {
        return playlistList;
    }

    protected PlaylistWrapper(Parcel in) {
        playlistList = in.createTypedArrayList(Playlist.CREATOR);
    }

    public static final Creator<PlaylistWrapper> CREATOR = new Creator<PlaylistWrapper>() {
        @Override
        public PlaylistWrapper createFromParcel(Parcel in) {
            return new PlaylistWrapper(in);
        }

        @Override
        public PlaylistWrapper[] newArray(int size) {
            return new PlaylistWrapper[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(playlistList);
    }
}
