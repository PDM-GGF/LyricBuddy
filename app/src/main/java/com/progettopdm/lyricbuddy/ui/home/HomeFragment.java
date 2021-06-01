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
import com.progettopdm.lyricbuddy.repository.ICCAuthRepository;
import com.progettopdm.lyricbuddy.repository.ISpotifyRepository;
import com.progettopdm.lyricbuddy.repository.SpotifyRepository;
import com.progettopdm.lyricbuddy.repository.callback.CCAuthCallback;
import com.progettopdm.lyricbuddy.repository.CCAuthRepository;
import com.progettopdm.lyricbuddy.repository.callback.SpotifyCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    HomeCardRecyclerViewAdapter newReleasesAdapter;
    HomeCardRecyclerViewAdapter featuredPlaylistsAdapter;


    List<Album> mNewReleasesList;
    TrackContainer clickedTrackContainer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNewReleasesList = new ArrayList<>();

        TextView featuredText = view.findViewById(R.id.featured_text);

        RecyclerView newReleasesRecyclerView = view.findViewById(R.id.new_releases_list);

        newReleasesAdapter = new HomeCardRecyclerViewAdapter(mNewReleasesList, new HomeCardRecyclerViewAdapter.OnItemClickListener() {
            //Click su elemento lista "Nuove Uscite"
            @Override
            public void onItemClick(TrackContainer trackContainer) {
                Log.d("Album", trackContainer.getName());
                clickedTrackContainer = trackContainer;
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
        newReleasesRecyclerView.setAdapter(newReleasesAdapter);

        ISpotifyRepository spotifyRepository =
                new SpotifyRepository(requireActivity().getApplication());

        ICCAuthRepository iccAuthRepository =
                new CCAuthRepository(requireActivity().getApplication());

        HomeViewModel homeViewModel = new ViewModelProvider(this, new HomeViewModelFactory(
                requireActivity().getApplication(), spotifyRepository, iccAuthRepository)).get(HomeViewModel.class);

        //Get token then newreleases
        homeViewModel.getSpotiToken().observe(getViewLifecycleOwner(), token ->{
            homeViewModel.getmNewReleases(token).observe(getViewLifecycleOwner(), response ->{

                if (response != null) {
                    //carica immagini
                    homeViewModel.loadImagesFromUrl(response.getAlbumWrapper().getAlbumList());

                    //carica tracklists
                    //homeViewModel.loadTrackList(response.getAlbumWrapper().getAlbumList());

                    updateUIForNewReleasesSuccess(response.getAlbumWrapper().getAlbumList());
                }else{
                    Log.d("FAILED: ", "HOME FRAGMENT COULDN'T FETCH");
                }
            });
        });

       /* RecyclerView featuredPlaylistsRecyclerView = view.findViewById(R.id.featured_playlist_list);
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
        }*/

    }

    private void updateUIForNewReleasesSuccess(List<Album> albumList) {
        mNewReleasesList.addAll(albumList);
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                newReleasesAdapter.notifyDataSetChanged();
            }
        });
    }
}
