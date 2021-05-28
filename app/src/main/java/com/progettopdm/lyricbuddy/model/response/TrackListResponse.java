package com.progettopdm.lyricbuddy.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.progettopdm.lyricbuddy.model.Track;

import java.util.List;

public class TrackListResponse implements Parcelable {
    @SerializedName("items")
    List<Track> trackList;

    public TrackListResponse(List<Track> trackList) {
        this.trackList = trackList;
    }

    protected TrackListResponse(Parcel in) {
        trackList = in.createTypedArrayList(Track.CREATOR);
    }

    public static final Creator<TrackListResponse> CREATOR = new Creator<TrackListResponse>() {
        @Override
        public TrackListResponse createFromParcel(Parcel in) {
            return new TrackListResponse(in);
        }

        @Override
        public TrackListResponse[] newArray(int size) {
            return new TrackListResponse[size];
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
