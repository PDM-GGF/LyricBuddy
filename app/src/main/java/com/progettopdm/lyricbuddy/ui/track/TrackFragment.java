package com.progettopdm.lyricbuddy.ui.track;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.repository.MxmLyricsCallback;
import com.progettopdm.lyricbuddy.repository.MxmLyricsRepository;
import com.progettopdm.lyricbuddy.repository.MxmMatcherCallback;
import com.progettopdm.lyricbuddy.repository.MxmMatcherRepository;
import com.progettopdm.lyricbuddy.ui.favorites.FavoritesViewModel;
import com.progettopdm.lyricbuddy.ui.userprofile.UserProfileViewModel;

public class TrackFragment extends Fragment implements MxmLyricsCallback{

    private TrackViewModel trackViewModel;

    private MxmLyricsRepository mxmLyricsRepository;
    private MxmMatcherRepository mxmMatcherRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("TRACKFRAGMENT", "Creato il fragment di track");
        mxmLyricsRepository = new MxmLyricsRepository(this);

        mxmLyricsRepository.fetchLyrics();

        trackViewModel =
                new ViewModelProvider(this).get(TrackViewModel.class);
        View root = inflater.inflate(R.layout.fragment_track, container, false);
        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        trackViewModel = new ViewModelProvider(this).get(TrackViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onLyricsGet(String lyrics) {
        Log.d("Lyrics DAL FRAGMENT", lyrics);
    }

    @Override
    public void onFailure(String msg) {

    }
}