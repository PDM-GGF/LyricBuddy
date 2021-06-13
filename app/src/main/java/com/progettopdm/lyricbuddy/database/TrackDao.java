package com.progettopdm.lyricbuddy.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.progettopdm.lyricbuddy.model.Track;

import java.util.List;

    @Dao
    public interface TrackDao {

        @Insert
        void insertTracks(List<Track> tracks);

        @Query("SELECT * FROM tracks")
        List<Track> getAllTrack();

        @Query("DELETE FROM tracks")
        void deleteAll();

        @Delete
        void deleteAllWithoutQuery(Track... tracks);

    }
