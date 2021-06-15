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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.repository.DatabaseRepository;
import com.progettopdm.lyricbuddy.repository.IDatabaseRepository;
import com.progettopdm.lyricbuddy.repository.MxmLyricsCallback;
import com.progettopdm.lyricbuddy.repository.MxmLyricsRepository;
import com.progettopdm.lyricbuddy.repository.MxmMatcherCallback;
import com.progettopdm.lyricbuddy.repository.MxmMatcherRepository;
import com.progettopdm.lyricbuddy.ui.tracklist.TrackListRecyclerViewAdapter;
import com.progettopdm.lyricbuddy.ui.tracklist.TrackListViewModel;
import com.progettopdm.lyricbuddy.ui.tracklist.TrackListViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel favoritesViewModel;
    TrackListViewModel trackListViewModel;

    private IDatabaseRepository iDatabaseRepository;

    TrackListRecyclerViewAdapter trackListRecyclerViewAdapter;

    List<Track> favList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iDatabaseRepository = new DatabaseRepository(getActivity().getApplication());

        trackListViewModel = new ViewModelProvider(requireActivity(),
                new TrackListViewModelFactory()).get(TrackListViewModel.class);

        favoritesViewModel =
                new ViewModelProvider(requireActivity(),
                        new FavoritesViewModelFactory(requireActivity().getApplication(), iDatabaseRepository))
                        .get(FavoritesViewModel.class);

        TextView noFavs = view.findViewById(R.id.text_no_favorites);

        favoritesViewModel.getmTracks().observe(getViewLifecycleOwner(), new Observer<List<Track>>() {
            @Override
            public void onChanged(List<Track> trackList) {

                if(trackList.size() == 0){
                    noFavs.setText(R.string.no_favs);
                }else{
                    noFavs.setText("");
                    updateResults(trackList);
                }

            }
        });

        RecyclerView searchResultsRecyclerView = view.findViewById(R.id.favorite_recycler_view);
        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        trackListRecyclerViewAdapter = new TrackListRecyclerViewAdapter(favList, new TrackListRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Track track) {

            }
        });
        searchResultsRecyclerView.setAdapter(trackListRecyclerViewAdapter);

        favoritesViewModel.getmTracks().observe(getViewLifecycleOwner(), new Observer<List<Track>>() {
            @Override
            public void onChanged(List<Track> tracks) {
                Log.d("Favorites: ", tracks.toString());
            }
        });

    }

    private void updateResults(List<Track> trackList) {
        favList.clear();
        favList.addAll(trackList);

        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                trackListRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }
}