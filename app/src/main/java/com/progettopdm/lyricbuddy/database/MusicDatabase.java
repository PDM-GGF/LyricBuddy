package com.progettopdm.lyricbuddy.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.progettopdm.lyricbuddy.model.Album;
import com.progettopdm.lyricbuddy.model.Artist;
import com.progettopdm.lyricbuddy.model.Playlist;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.utils.Constants;

@Database(version = 1, entities={Track.class, Album.class, Artist.class, Playlist.class})
public abstract class MusicDatabase extends RoomDatabase {
    public abstract  TrackDao TrackDao();
    public abstract  AlbumDao AlbumDao();
    public abstract  ArtistDao ArtistDao();
    public abstract  PlaylistDao PlaylistDao();

    public static MusicDatabase INSTANCE;

    public static MusicDatabase getDatabase(Context context){
        if(INSTANCE == null) {
            synchronized (MusicDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MusicDatabase.class, Constants.DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
