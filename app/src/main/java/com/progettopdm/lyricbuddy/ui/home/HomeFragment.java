package com.progettopdm.lyricbuddy.ui.home;

import android.os.Bundle;
import android.util.JsonReader;
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
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.model.Album;
import com.progettopdm.lyricbuddy.model.GenericImage;
import com.progettopdm.lyricbuddy.model.Playlist;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.model.TrackContainer;
import com.progettopdm.lyricbuddy.model.response.FeaturedResponse;
import com.progettopdm.lyricbuddy.model.response.NewReleaseResponse;
import com.progettopdm.lyricbuddy.model.response.TrackListResponse;
import com.progettopdm.lyricbuddy.repository.CCAuthRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HomeViewModel homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        TextView featuredText = view.findViewById(R.id.featured_text);

        RecyclerView newReleasesRecyclerView = view.findViewById(R.id.new_releases_list);
        try {
            homeViewModel.getNewReleases().observe(this.getViewLifecycleOwner(), new Observer<List<Album>>() {
                @Override
                public void onChanged(List<Album> albumList) {
                    HomeCardRecyclerViewAdapter homeCardRecyclerViewAdapter = new HomeCardRecyclerViewAdapter(albumList, new HomeCardRecyclerViewAdapter.OnItemClickListener() {
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
                    newReleasesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),  1,
                            GridLayoutManager.HORIZONTAL, false));
                    newReleasesRecyclerView.setAdapter(homeCardRecyclerViewAdapter);
                }
            });
        } catch (IOException e) {
            //e.printStackTrace();
        }


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
}
