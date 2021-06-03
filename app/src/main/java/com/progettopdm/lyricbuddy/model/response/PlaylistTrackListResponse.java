package com.progettopdm.lyricbuddy.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.model.response.wrappers.TrackWrapper;

import java.util.List;

public class PlaylistTrackListResponse implements Parcelable {
    @SerializedName("items")
    List<TrackWrapper> trackWrapperList;

    public PlaylistTrackListResponse(List<TrackWrapper> trackWrapper) {
        this.trackWrapperList = trackWrapper;
    }

    protected PlaylistTrackListResponse(Parcel in) {
        trackWrapperList = in.createTypedArrayList(TrackWrapper.CREATOR);
    }

    public static final Creator<PlaylistTrackListResponse> CREATOR = new Creator<PlaylistTrackListResponse>() {
        @Override
        public PlaylistTrackListResponse createFromParcel(Parcel in) {
            return new PlaylistTrackListResponse(in);
        }

        @Override
        public PlaylistTrackListResponse[] newArray(int size) {
            return new PlaylistTrackListResponse[size];
        }
    };

    public List<TrackWrapper> getTrackWrapperList() {
        return trackWrapperList;
    }

    public void setTrackWrapperList(List<TrackWrapper> trackWrapper) {
        this.trackWrapperList = trackWrapper;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(trackWrapperList);
    }
}
