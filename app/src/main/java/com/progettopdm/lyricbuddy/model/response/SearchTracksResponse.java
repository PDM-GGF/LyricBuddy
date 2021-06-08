package com.progettopdm.lyricbuddy.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.progettopdm.lyricbuddy.model.response.wrappers.SearchTrackWrapper;

public class SearchTracksResponse implements Parcelable {

    @SerializedName("tracks")
    SearchTrackWrapper trackWrapper;

    public SearchTrackWrapper getTrackWrapper() {
        return trackWrapper;
    }

    public void setTrackWrapper(SearchTrackWrapper tracks) {
        this.trackWrapper = tracks;
    }

    protected SearchTracksResponse(Parcel in) {
    }

    public static final Creator<SearchTracksResponse> CREATOR = new Creator<SearchTracksResponse>() {
        @Override
        public SearchTracksResponse createFromParcel(Parcel in) {
            return new SearchTracksResponse(in);
        }

        @Override
        public SearchTracksResponse[] newArray(int size) {
            return new SearchTracksResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
