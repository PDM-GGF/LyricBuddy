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
    List<Playlist> mFeaturedList;
    String featuredText;

    HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNewReleasesList = new ArrayList<>();
        mFeaturedList = new ArrayList<>();

        TextView featuredTextView = view.findViewById(R.id.featured_text);

        //NEW RELEASES RECYCLER VIEW
        RecyclerView newReleasesRecyclerView = view.findViewById(R.id.new_releases_list);
        newReleasesAdapter = new HomeCardRecyclerViewAdapter(mNewReleasesList, new HomeCardRecyclerViewAdapter.OnItemClickListener() {
            //Click su elemento lista "Nuove Uscite"
            @Override
            public void onItemClick(TrackContainer trackContainer) {
                homeViewModel.mClickedTrackContainer = trackContainer;

                //GET TRACKLIST
                homeViewModel.getSpotiToken().observe(getViewLifecycleOwner(), token -> {
                            homeViewModel.getmAlbumTracklistLiveData((Album) trackContainer, token).observe(getViewLifecycleOwner(), tracks ->{
                                Log.d("ALBUM: ", trackContainer.getName());
                                Log.d("TRACKS: ", tracks.getTrackList().get(0).getName());
                                trackContainer.setTrackList(tracks.getTrackList());
                                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                                navController.navigate(R.id.action_global_navigation_tracklist);
                            });

                        });


            }
        });
        newReleasesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),  1,
                GridLayoutManager.HORIZONTAL, false));
        newReleasesRecyclerView.setAdapter(newReleasesAdapter);

        //FEATURED PLAYLISTS RECYCLER VIEW
        RecyclerView featuredPlaylistsRecyclerView = view.findViewById(R.id.featured_playlist_list);
        featuredPlaylistsAdapter = new HomeCardRecyclerViewAdapter(mFeaturedList, new HomeCardRecyclerViewAdapter.OnItemClickListener() {
            //Click su elemento lista "Featured"
            @Override
            public void onItemClick(TrackContainer trackContainer) {
                homeViewModel.mClickedTrackContainer = trackContainer;
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_global_navigation_tracklist);
            }
        });
        featuredPlaylistsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),  1,
                GridLayoutManager.HORIZONTAL, false));
        featuredPlaylistsRecyclerView.setAdapter(featuredPlaylistsAdapter);



        //DATA FETCH FROM VIEWMODEL
        ISpotifyRepository spotifyRepository =
                new SpotifyRepository(requireActivity().getApplication());

        ICCAuthRepository iccAuthRepository =
                new CCAuthRepository(requireActivity().getApplication());

        homeViewModel = new ViewModelProvider(requireActivity(), new HomeViewModelFactory(
                requireActivity().getApplication(), spotifyRepository, iccAuthRepository)).get(HomeViewModel.class);


        //Get token then new releases
        homeViewModel.getSpotiToken().observe(getViewLifecycleOwner(), token ->{
            homeViewModel.getmNewReleases(token).observe(getViewLifecycleOwner(), response ->{

                if (response != null) {
                    List<Album> albumList = response.getAlbumWrapper().getAlbumList();

                    //carica immagini
                    homeViewModel.loadImagesFromUrl(albumList);

                    updateUIForNewReleasesSuccess(albumList);
                }else{
                    Log.d("FAILED: ", "HOME FRAGMENT COULDN'T FETCH");
                }
            });
        });

        //Get token then featured playlists
        homeViewModel.getSpotiToken().observe(getViewLifecycleOwner(), token ->{
            homeViewModel.getmFeaturedPlaylists(token).observe(getViewLifecycleOwner(), response ->{

                if (response != null) {
                    List<Playlist> playlistList = response.getPlaylistWrapper().getPlaylistList();

                    featuredText = response.getMessage();

                    featuredTextView.setText(featuredText);

                    //carica immagini
                    homeViewModel.loadImagesFromUrl(playlistList);

                    //carica tracklists
                    //homeViewModel.loadPlaylistTracklist(response.getPlaylistWrapper().getPlaylistList(), token);

                    updateUIForFeaturedSuccess(playlistList);
                }else{
                    Log.d("FAILED: ", "HOME FRAGMENT COULDN'T FETCH");
                }
            });
        });



    }

    private void updateUIForFeaturedSuccess(List<Playlist> playlistList) {
        mFeaturedList.clear();
        mFeaturedList.addAll(playlistList);

        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                featuredPlaylistsAdapter.notifyDataSetChanged();
            }
        });
    }

    private void updateUIForNewReleasesSuccess(List<Album> albumList) {
        mNewReleasesList.clear();
        mNewReleasesList.addAll(albumList);
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                newReleasesAdapter.notifyDataSetChanged();
            }
        });
    }
}
