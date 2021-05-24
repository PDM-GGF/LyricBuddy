package com.progettopdm.lyricbuddy.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TrackAlbum {
    @Embedded
    public Track track;
    @Relation(
            parentColumn = "albumId",
            entityColumn = "albumTrackId"
    )
    public List<Track> tracks;
}

