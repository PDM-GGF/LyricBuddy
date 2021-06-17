package com.progettopdm.lyricbuddy.database;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.progettopdm.lyricbuddy.model.Artist;
import com.progettopdm.lyricbuddy.model.Track;

import java.util.List;

public interface ArtistDao {

    @Insert
    void insertArtist(Artist artist);

    @Query("SELECT * FROM artists")
    LiveData<List<Track>> getAllArtists();

    @Query("DELETE FROM artists")
    void deleteAll();

    @Delete
    void deleteArtist(Artist artist);

    @Delete
    void deleteAllWithoutQuery(Artist... artists);

}
