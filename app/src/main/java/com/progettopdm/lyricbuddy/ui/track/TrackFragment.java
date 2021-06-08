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
import com.progettopdm.lyricbuddy.ui.home.HomeViewModel;
import com.progettopdm.lyricbuddy.ui.tracklist.TrackListViewModel;
import com.progettopdm.lyricbuddy.ui.userprofile.UserProfileViewModel;

public class TrackFragment extends Fragment implements MxmLyricsCallback, MxmMatcherCallback {

    private MxmLyricsRepository mxmLyricsRepository;
    private MxmMatcherRepository mxmMatcherRepository;

    private View root;
    private TrackListViewModel trackListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_track, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        trackListViewModel = new ViewModelProvider(requireActivity()).get(TrackListViewModel.class);


        String q_track = trackListViewModel.getmClickedTrack().getName();
        String q_artist = trackListViewModel.getmClickedArtist();
        //String q_track = "Formidable";
        //String q_artist = "Twenty One Pilots";

        TextView trackName = root.findViewById(R.id.track_name);
        TextView trackArtist = root.findViewById(R.id.track_artist);

        trackName.setText(q_track);
        trackArtist.setText(q_artist);

        int id = 215778095;
        super.onActivityCreated(savedInstanceState);

        mxmMatcherRepository = new MxmMatcherRepository(this);
        mxmMatcherRepository.fetchTrackId(q_track, q_artist);

    }

    @Override
    public void onLyricsGet(String lyrics) {
        TextView trackLyrics = root.findViewById(R.id.track_lyrics);
        trackLyrics.setText(lyrics);
    }

    @Override
    public void onLyricsFailure(String msg) {

    }

    @Override
    public void onIdGet(int id) {
        mxmLyricsRepository = new MxmLyricsRepository(this);
        mxmLyricsRepository.fetchLyrics(id);
    }

    @Override
    public void onMatcherFailure(String msg) {

    }

}