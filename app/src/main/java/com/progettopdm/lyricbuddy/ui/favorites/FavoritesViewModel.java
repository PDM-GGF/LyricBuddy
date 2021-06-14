package com.progettopdm.lyricbuddy.ui.favorites;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.progettopdm.lyricbuddy.database.TrackDao;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.repository.DatabaseRepository;
import com.progettopdm.lyricbuddy.repository.IDatabaseRepository;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {

   private LiveData<List<Track>> mTracks;
   private IDatabaseRepository iDatabaseRepository;

   public FavoritesViewModel(Application application, IDatabaseRepository iDatabaseRepository) {
        super(application);
        this.mTracks = new MutableLiveData<>();
        this.iDatabaseRepository = iDatabaseRepository;
    }


    public LiveData<List<Track>> getmTracks() {
       loadFavorites();
       return mTracks;
    }

    private void loadFavorites() {
        mTracks = iDatabaseRepository.fetchFavorites();
    }
}