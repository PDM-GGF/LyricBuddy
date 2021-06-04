package com.progettopdm.lyricbuddy.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.progettopdm.lyricbuddy.model.Track;

import java.util.List;

public class AlbumTrackListResponse implements Parcelable {
    @SerializedName("items")
    List<Track> trackList;

    public AlbumTrackListResponse(List<Track> trackList) {
        this.trackList = trackList;
    }

    protected AlbumTrackListResponse(Parcel in) {
        trackList = in.createTypedArrayList(Track.CREATOR);
    }

    public static final Creator<AlbumTrackListResponse> CREATOR = new Creator<AlbumTrackListResponse>() {
        @Override
        public AlbumTrackListResponse createFromParcel(Parcel in) {
            return new AlbumTrackListResponse(in);
        }

        @Override
        public AlbumTrackListResponse[] newArray(int size) {
            return new AlbumTrackListResponse[size];
        }
    };

    public List<Track> getTrackList() {
        return trackList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(trackList);
    }
}
