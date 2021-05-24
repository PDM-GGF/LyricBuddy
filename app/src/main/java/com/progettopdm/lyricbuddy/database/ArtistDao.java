package com.progettopdm.lyricbuddy.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.progettopdm.lyricbuddy.model.Artist;
import com.progettopdm.lyricbuddy.model.TrackAlbum;
import com.progettopdm.lyricbuddy.model.TrackArtist;

import java.util.List;

@Dao
public interface ArtistDao {
    @Insert
    void insertArtists(List<Artist> artists);

    @Query("SELECT * FROM artists")
    List<Artist> getAllArtists();

    @Query("DELETE FROM artists")
    void deleteAll();

    @Delete
    void deleteAllWithoutQuery(Artist... artists);

    @Transaction
    @Query("SELECT * FROM artists")
    public List<TrackArtist> getTrackArtist();
}
