package com.progettopdm.lyricbuddy.model.response.wrappers;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.progettopdm.lyricbuddy.model.Track;

import java.util.List;

public class SearchTrackWrapper implements Parcelable {

    @SerializedName("items")
    List<Track> trackList;

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    protected SearchTrackWrapper(Parcel in) {
        trackList = in.createTypedArrayList(Track.CREATOR);
    }

    public static final Creator<SearchTrackWrapper> CREATOR = new Creator<SearchTrackWrapper>() {
        @Override
        public SearchTrackWrapper createFromParcel(Parcel in) {
            return new SearchTrackWrapper(in);
        }

        @Override
        public SearchTrackWrapper[] newArray(int size) {
            return new SearchTrackWrapper[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(trackList);
    }
}
