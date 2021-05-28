package com.progettopdm.lyricbuddy.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.progettopdm.lyricbuddy.model.Album;
//import com.progettopdm.lyricbuddy.model.TrackAlbum;

import java.util.List;

@Dao
public interface AlbumDao {
    @Insert
    void insertAlbums(List<Album> albums);

    @Query("SELECT * FROM albums")
    List<Album> getAllAlbums();

    @Query("DELETE FROM albums")
    void deleteAll();

    @Delete
    void deleteAllWithoutQuery(Album... albums);

}
