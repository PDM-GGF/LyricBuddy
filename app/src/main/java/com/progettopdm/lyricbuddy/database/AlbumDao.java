package com.progettopdm.lyricbuddy.database;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.progettopdm.lyricbuddy.model.Album;
import com.progettopdm.lyricbuddy.model.Artist;
import com.progettopdm.lyricbuddy.model.Track;

import java.util.List;

public interface AlbumDao {

    @Insert
    void insertArtist(Album album);

    @Query("SELECT * FROM albums")
    LiveData<List<Track>> getAllAlbums();

    @Query("DELETE FROM albums")
    void deleteAll();

    @Delete
    void deleteAlbum(Album album);

    @Delete
    void deleteAllWithoutQuery(Album... albums);
}
