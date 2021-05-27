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

    int trackId;
    int artistId;

    public TrackArtist(int trackId, int artistId) {
        this.trackId = trackId;
        this.artistId = artistId;
    }

    public TrackArtist() {
    }

    protected TrackArtist(Parcel in) {
        trackId = in.readInt();
        artistId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(trackId);
        dest.writeInt(artistId);
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

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    //METODO PER RELAZIONE MOLTI A MOLTI **DA DEFINIRE**
    @Embedded
    public Artist artist;
    @Relation(
            parentColumn = "trackId",
            entityColumn = "artistId",
            associateBy = @Junction(TrackArtist.class)
    )
    public List<Track> tracks;
}
