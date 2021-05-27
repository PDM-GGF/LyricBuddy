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

    int trackId;
    int playlistId;


    public TrackPlaylist(int trackId, int playlistId) {
        this.trackId = trackId;
        this.playlistId = playlistId;

    }

    public TrackPlaylist(){

    }

    public int getTrackId() {
        return trackId;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    //METODO PER RELAZIONE MOLTI A MOLTI **DA DEFINIRE**
    @Embedded
    public Playlist playlist;
    @Relation(
            parentColumn = "playlistId",
            entityColumn = "songId",
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
        trackId = in.readInt();
        playlistId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(trackId);
        dest.writeInt(playlistId);
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
