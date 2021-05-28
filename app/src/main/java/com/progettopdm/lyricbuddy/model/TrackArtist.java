package com.progettopdm.lyricbuddy.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

@Entity(primaryKeys = {"trackId", "artistId"})
public class TrackArtist implements Parcelable {

    String trackId;
    String artistId;

    public TrackArtist(String trackId, String artistId) {
        this.trackId = trackId;
        this.artistId = artistId;
    }

    protected TrackArtist(Parcel in) {
        trackId = in.readString();
        artistId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(trackId);
        dest.writeString(artistId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TrackArtist> CREATOR = new Creator<TrackArtist>() {
        @Override
        public TrackArtist createFromParcel(Parcel in) {
            return new TrackArtist(in);
        }

        @Override
        public TrackArtist[] newArray(int size) {
            return new TrackArtist[size];
        }
    };

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    //METODO PER RELAZIONE MOLTI A MOLTI **DA DEFINIRE**
    @Embedded
    public Artist artist;
    @Relation(
            parentColumn = "trackId",
            entityColumn = "artistTrackId",
            associateBy = @Junction(TrackArtist.class)
    )
    public List<Track> tracks;
}
