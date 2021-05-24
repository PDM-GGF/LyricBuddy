package com.progettopdm.lyricbuddy.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.progettopdm.lyricbuddy.database.ArtistDao;
import com.progettopdm.lyricbuddy.model.Artist;
import com.progettopdm.lyricbuddy.utils.Constants;

@Database(entities = {Artist.class}, version = Constants.DATABASE_VERSION)
public abstract class ArtistRoomDatabase extends RoomDatabase {
    private static volatile ArtistRoomDatabase INSTANCE;
    public abstract ArtistDao artistDao();

    public static ArtistRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ArtistRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ArtistRoomDatabase.class, Constants.DATABASE_NAME).build();
                }
            }
        }
        return INSTANCE;
    }
}
