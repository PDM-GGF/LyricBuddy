package com.progettopdm.lyricbuddy.ui.tracklist;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.progettopdm.lyricbuddy.model.GenericImage;
import com.progettopdm.lyricbuddy.model.Track;

public class TrackListViewModel extends ViewModel {

    Track mClickedTrack;
    String mClickedArtist;


    GenericImage mClickedImage;

    public Track getmClickedTrack() {
        return mClickedTrack;
    }

    public void setmClickedTrack(Track track) {
        this.mClickedTrack = track;
    }

    public String getmClickedArtist() {
        return mClickedArtist;
    }

    public GenericImage getmClickedImage() {
        return mClickedImage;
    }

    public TrackListViewModel() {

    }
}