package com.progettopdm.lyricbuddy.model.response.wrappers;

import android.os.Parcel;
import android.os.Parcelable;

import com.progettopdm.lyricbuddy.model.Track;

public class TrackWrapper implements Parcelable {

    Track track;

    public TrackWrapper(Track track) {
        this.track = track;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    protected TrackWrapper(Parcel in) {
        track = in.readParcelable(Track.class.getClassLoader());
    }

    public static final Creator<TrackWrapper> CREATOR = new Creator<TrackWrapper>() {
        @Override
        public TrackWrapper createFromParcel(Parcel in) {
            return new TrackWrapper(in);
        }

        @Override
        public TrackWrapper[] newArray(int size) {
            return new TrackWrapper[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(track, flags);
    }
}
