package com.progettopdm.lyricbuddy.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.progettopdm.lyricbuddy.model.Track;

import java.util.List;

@Dao
public interface TrackDao {

    @Insert
    void insertTrack(Track track);

    @Query("SELECT * FROM tracks")
    LiveData<List<Track>> getAllTracks();

    @Query("DELETE FROM tracks")
    void deleteAll();

    @Delete
    void deleteTrack(Track track);

    @Delete
    void deleteAllWithoutQuery(Track... tracks);

}
