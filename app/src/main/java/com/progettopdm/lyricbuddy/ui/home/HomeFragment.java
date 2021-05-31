package com.progettopdm.lyricbuddy.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.model.Album;
import com.progettopdm.lyricbuddy.model.Playlist;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.model.TrackContainer;
import com.progettopdm.lyricbuddy.repository.SpotifyRepository;
import com.progettopdm.lyricbuddy.repository.callback.CCAuthCallback;
import com.progettopdm.lyricbuddy.repository.CCAuthRepository;
import com.progettopdm.lyricbuddy.repository.callback.SpotifyCallback;

import java.io.IOException;
import java.util.List;

public class HomeFragment extends Fragment implements CCAuthCallback, SpotifyCallback {

    HomeViewModel homeViewModel;
    CCAuthRepository ccAuthRepository;
    SpotifyRepository spotifyRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        ccAuthRepository = new CCAuthRepository(this, requireActivity().getApplication());
        spotifyRepository = new SpotifyRepository(this, requireActivity().getApplication());

        ccAuthRepository.authorize();


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TextView featuredText = view.findViewById(R.id.featured_text);

        RecyclerView newReleasesRecyclerView = view.findViewById(R.id.new_releases_list);



        HomeCardRecyclerViewAdapter homeCardRecyclerViewAdapter = null;
        try {
            homeCardRecyclerViewAdapter = new HomeCardRecyclerViewAdapter(homeViewModel.getmNewReleases().getValue(), new HomeCardRecyclerViewAdapter.OnItemClickListener() {
                //Click su elemento lista "Nuove Uscite"
                @Override
                public void onItemClick(TrackContainer trackContainer) {
                    Log.d("Album", trackContainer.getName());
                    homeViewModel.setmClickedTrackContainer(trackContainer);
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.navigate(R.id.action_global_navigation_tracklist);
                    Log.d("Tracklist: ", "");
                    for(Track t : trackContainer.getTrackList()){
                        Log.d("", t.getName());
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        newReleasesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),  1,
                GridLayoutManager.HORIZONTAL, false));
        newReleasesRecyclerView.setAdapter(homeCardRecyclerViewAdapter);




        RecyclerView featuredPlaylistsRecyclerView = view.findViewById(R.id.featured_playlist_list);
        try {
            homeViewModel.getFeaturedPlaylists().observe(this.getViewLifecycleOwner(), new Observer<List<Playlist>>() {
                @Override
                public void onChanged(List<Playlist> playlistList) {
                    HomeCardRecyclerViewAdapter playlistRecyclerViewAdapter = new HomeCardRecyclerViewAdapter(playlistList, new HomeCardRecyclerViewAdapter.OnItemClickListener() {
                        //Click su elemento lista "Featured"
                        @Override
                        public void onItemClick(TrackContainer trackContainer) {
                        Log.d("Playlist", trackContainer.getName());
                        }
                    });
                    featuredText.setText(homeViewModel.mFeaturedMessage);
                    featuredPlaylistsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),  1,
                            GridLayoutManager.HORIZONTAL, false));
                    featuredPlaylistsRecyclerView.setAdapter(playlistRecyclerViewAdapter);
                }
            });
        } catch (IOException e) {
           //e.printStackTrace();
        }

    }

    @Override
    public void onAuthResponse(String token) {
        homeViewModel.setSpotiToken(token);
        Log.d("TOKEN ON RESPONSE: ", homeViewModel.spotiToken);
    }

    @Override
    public void onAuthFailure(String msg) {
        Log.d("ERROR: ", msg);
    }

    @Override
    public void onNewReleaseResponse(List<Album> newReleasesList) {
        homeViewModel.setmNewReleases(new MutableLiveData<>(newReleasesList));
    }

    @Override
    public void onNewReleaseFailure(String msg) {
        ccAuthRepository.authorize();
        spotifyRepository.getNewReleases(homeViewModel.spotiToken);
    }
}
