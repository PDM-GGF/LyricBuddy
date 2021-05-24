package com.progettopdm.lyricbuddy.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.progettopdm.lyricbuddy.database.PlaylistDao;
import com.progettopdm.lyricbuddy.model.Playlist;
import com.progettopdm.lyricbuddy.utils.Constants;

@Database(entities = {Playlist.class}, version = Constants.DATABASE_VERSION)
public abstract class PlaylistRoomDatabase extends RoomDatabase {
    private static volatile PlaylistRoomDatabase INSTANCE;
    public abstract PlaylistDao playlistDao();

    public static PlaylistRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PlaylistRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            PlaylistRoomDatabase.class, Constants.DATABASE_NAME).build();
                }
            }
        }
        return INSTANCE;
    }
}
