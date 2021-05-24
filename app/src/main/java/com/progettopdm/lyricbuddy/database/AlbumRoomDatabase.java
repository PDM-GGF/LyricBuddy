package com.progettopdm.lyricbuddy.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.progettopdm.lyricbuddy.database.AlbumDao;
import com.progettopdm.lyricbuddy.model.Album;
import com.progettopdm.lyricbuddy.utils.Constants;

@Database(entities = {Album.class}, version = Constants.DATABASE_VERSION)
public abstract class AlbumRoomDatabase extends RoomDatabase {
    private static volatile AlbumRoomDatabase INSTANCE;
    public abstract AlbumDao albumDao();

    public static AlbumRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AlbumRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AlbumRoomDatabase.class, Constants.DATABASE_NAME).build();
                }
            }
        }
        return INSTANCE;
    }
}
