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
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.repository.DatabaseRepository;
import com.progettopdm.lyricbuddy.repository.IDatabaseRepository;
import com.progettopdm.lyricbuddy.repository.MxmLyricsCallback;
import com.progettopdm.lyricbuddy.repository.MxmLyricsRepository;
import com.progettopdm.lyricbuddy.repository.MxmMatcherCallback;
import com.progettopdm.lyricbuddy.repository.MxmMatcherRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel favoritesViewModel;
    private IDatabaseRepository iDatabaseRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iDatabaseRepository = new DatabaseRepository(getActivity().getApplication());

        favoritesViewModel =
                new ViewModelProvider(requireActivity(),
                        new FavoritesViewModelFactory(requireActivity().getApplication(), iDatabaseRepository))
                        .get(FavoritesViewModel.class);


        favoritesViewModel.getmTracks().observe(getViewLifecycleOwner(), new Observer<List<Track>>() {
            @Override
            public void onChanged(List<Track> tracks) {
                Log.d("Favorites: ", tracks.toString());
            }
        });

    }
}