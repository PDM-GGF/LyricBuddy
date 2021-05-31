package com.progettopdm.lyricbuddy.ui.favorites;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.repository.MxmLyricsCallback;
import com.progettopdm.lyricbuddy.repository.MxmLyricsRepository;
import com.progettopdm.lyricbuddy.repository.MxmMatcherCallback;
import com.progettopdm.lyricbuddy.repository.MxmMatcherRepository;

public class FavoritesFragment extends Fragment implements MxmLyricsCallback, MxmMatcherCallback {

    private FavoritesViewModel favoritesViewModel;

    private MxmLyricsRepository mxmLyricsRepository;
    private MxmMatcherRepository mxmMatcherRepository;
    private String track;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mxmLyricsRepository = new MxmLyricsRepository(this);
        //mxmMatcherRepository = new MxmMatcherRepository(this);

        track = new String();

        mxmLyricsRepository.fetchLyrics();
        //mxmMatcherRepository.fetchTrackId();

        favoritesViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);
        final TextView textView = root.findViewById(R.id.text_favorites);
        favoritesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onResponse(String track) {
        Log.d("TRACK dal fragment: ", track.toString());
    }

    @Override
    public void onFailure(String msg) {

    }
}