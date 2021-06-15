package com.progettopdm.lyricbuddy.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.progettopdm.lyricbuddy.model.Album;
import com.progettopdm.lyricbuddy.model.Artist;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.utils.Constants;

@Database(entities = {Track.class, Artist.class, /*Album.class*/}, version = 2)
@TypeConverters({Converters.class})
public abstract class TrackRoomDatabase extends RoomDatabase {

    private static volatile TrackRoomDatabase INSTANCE;
    //private static Converters convertersInstance;
    public abstract TrackDao TrackDao();



    public static TrackRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TrackRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            TrackRoomDatabase.class, Constants.DATABASE_NAME).build();
                }
            }
        }
        return INSTANCE;
    }
}

