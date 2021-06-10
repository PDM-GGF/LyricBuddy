package com.progettopdm.lyricbuddy.ui.search;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.model.Artist;
import com.progettopdm.lyricbuddy.model.Playlist;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.repository.CCAuthRepository;
import com.progettopdm.lyricbuddy.repository.ICCAuthRepository;
import com.progettopdm.lyricbuddy.repository.ISpotifyRepository;
import com.progettopdm.lyricbuddy.repository.SpotifyRepository;
import com.progettopdm.lyricbuddy.ui.home.HomeViewModel;
import com.progettopdm.lyricbuddy.ui.home.HomeViewModelFactory;
import com.progettopdm.lyricbuddy.ui.search.SearchViewModel;
import com.progettopdm.lyricbuddy.ui.tracklist.TrackListRecyclerViewAdapter;
import com.progettopdm.lyricbuddy.ui.tracklist.TrackListViewModel;
import com.progettopdm.lyricbuddy.ui.tracklist.TrackListViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    List<Track> mSearchResultsList = new ArrayList<>();

    TrackListViewModel trackListViewModel;

    private HomeViewModel homeViewModel;

    TrackListRecyclerViewAdapter trackListRecyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);
        return root;

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        trackListViewModel = new ViewModelProvider(requireActivity(), new TrackListViewModelFactory()).get(TrackListViewModel.class);

        //DATA FETCH FROM VIEWMODEL
        ISpotifyRepository spotifyRepository =
                new SpotifyRepository(requireActivity().getApplication());

        ICCAuthRepository iccAuthRepository =
                new CCAuthRepository(requireActivity().getApplication());

        homeViewModel = new ViewModelProvider(requireActivity(), new HomeViewModelFactory(
                requireActivity().getApplication(), spotifyRepository, iccAuthRepository)).get(HomeViewModel.class);

        RecyclerView searchResultsRecyclerView = view.findViewById(R.id.results_recycler);
        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        trackListRecyclerViewAdapter = new TrackListRecyclerViewAdapter(mSearchResultsList, new TrackListRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Track track) {
                trackListViewModel.setmClickedTrack(track);
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_global_navigation_track);
            }
        });
        searchResultsRecyclerView.setAdapter(trackListRecyclerViewAdapter);

        SearchView searchBar = view.findViewById(R.id.search_bar);

        searchBar.setQueryHint(getString(R.string.search_hint));


        SearchView.OnQueryTextListener search = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //TEST SEARCH
                homeViewModel.getSpotiToken().observe(getViewLifecycleOwner(), token ->{
                    homeViewModel.getmSearchedTracksLiveData(query, token).observe(getViewLifecycleOwner(), tracks ->{
                        List<Track> trackList = tracks.getTrackWrapper().getTrackList();
                        updateResults(trackList);
                        searchBar.clearFocus();
                    });
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        };

        searchBar.setOnQueryTextListener(search);




    }

    private void updateResults(List<Track> playlistList) {
        mSearchResultsList.clear();
        mSearchResultsList.addAll(playlistList);

        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                trackListRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }
}