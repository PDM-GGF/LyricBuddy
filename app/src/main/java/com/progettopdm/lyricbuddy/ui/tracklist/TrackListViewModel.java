package com.progettopdm.lyricbuddy.ui.tracklist;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.progettopdm.lyricbuddy.model.Track;

public class TrackListViewModel extends ViewModel {

    Track mClickedTrack;

    String mClickedArtist;

    public Track getmClickedTrack() {
        return mClickedTrack;
    }

    public String getmClickedArtist() {
        return mClickedArtist;
    }

    public TrackListViewModel() {

    }
}