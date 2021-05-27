package com.progettopdm.lyricbuddy.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.progettopdm.lyricbuddy.model.Playlist;
import com.progettopdm.lyricbuddy.model.TrackAlbum;
import com.progettopdm.lyricbuddy.model.TrackPlaylist;

import java.util.List;

@Dao
public interface PlaylistDao {
    @Insert
    void insertPlaylists(List<Playlist> playlists);

    @Query("SELECT * FROM playlists")
    List<Playlist> getAllPlaylists();

    @Query("DELETE FROM playlists")
    void deleteAll();

    @Delete
    void deleteAllWithoutQuery(Playlist... playlists);

    @Transaction
    @Query("SELECT * FROM playlists")
    public List<TrackPlaylist> getTrackPlaylist();
}
