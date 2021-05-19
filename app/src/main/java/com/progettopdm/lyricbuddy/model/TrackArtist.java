package com.progettopdm.lyricbuddy.model;

public class TrackArtist{

    int trackId;
    int artistId;

    public TrackArtist(int trackId, int artistId) {
        this.trackId = trackId;
        this.artistId = artistId;
    }

    public TrackArtist() {
    }

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
}
