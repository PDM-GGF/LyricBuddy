package com.progettopdm.lyricbuddy.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.progettopdm.lyricbuddy.database.TrackDao;
import com.progettopdm.lyricbuddy.database.TrackRoomDatabase;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.utils.ServiceLocator;

import java.util.List;

public class DatabaseRepository implements IDatabaseRepository {

    private final Application application;
    private final TrackDao tTrackDao;

    public DatabaseRepository(Application application) {
        this.application = application;
        TrackRoomDatabase db = ServiceLocator.getInstance().getTrackDao(application);
        this.tTrackDao = db.TrackDao();
    }

    public LiveData<List<Track>> fetchFavorites() {

        LiveData<List<Track>> mFavorites = tTrackDao.getAllTracks();
        return mFavorites;
    }


}
