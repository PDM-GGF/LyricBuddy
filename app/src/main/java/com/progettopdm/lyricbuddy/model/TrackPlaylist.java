package com.progettopdm.lyricbuddy.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

@Entity(primaryKeys = {"trackId", "playlistId"})
public class TrackPlaylist implements Parcelable {

    String trackId;
    String playlistId;


    public TrackPlaylist(String trackId, String playlistId) {
        this.trackId = trackId;
        this.playlistId = playlistId;

    }

    public TrackPlaylist(){

    }

    public String getTrackId() {
        return trackId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    //METODO PER RELAZIONE MOLTI A MOLTI **DA DEFINIRE**
    @Embedded
    public Playlist playlist;
    @Relation(
            parentColumn = "playlistId",
            entityColumn = "playlistTrackId",
            associateBy = @Junction(TrackPlaylist.class)
    )
    public List<Track> tracks;



    @Override
    public String toString() {
        return "TrackPlaylist{" +
                "trackid=" + trackId +
                "playlistId=" + playlistId +
                '}';
    }

    protected TrackPlaylist(Parcel in) {
        trackId = in.readString();
        playlistId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(trackId);
        dest.writeString(playlistId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TrackPlaylist> CREATOR = new Creator<TrackPlaylist>() {
        @Override
        public TrackPlaylist createFromParcel(Parcel in) {
            return new TrackPlaylist(in);
        }

        @Override
        public TrackPlaylist[] newArray(int size) {
            return new TrackPlaylist[size];
        }
    };



}
