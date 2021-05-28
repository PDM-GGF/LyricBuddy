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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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

    private HomeViewModel homeViewModel;
    List<Album> newReleasesList;
    List<Playlist> featuredPlaylistsList;
    String featuredMessage;

    CCAuthRepository ccAuthRepository = new CCAuthRepository();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ccAuthRepository.authorize();
        try {
            newReleasesList = getNewReleases();
            featuredPlaylistsList = getFeaturedPlaylists();
        } catch (IOException e) {
            //e.printStackTrace();
        }

        loadAlbumImages(newReleasesList);
        loadPlaylistImages(featuredPlaylistsList);

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView featuredText = view.findViewById(R.id.featured_text);
        featuredText.setText(featuredMessage);

        RecyclerView newReleasesRecyclerView = view.findViewById(R.id.new_releases_list);
        RecyclerView featuredPlaylistsRecyclerView = view.findViewById(R.id.featured_playlist_list);


        HomeCardRecyclerViewAdapter homeCardRecyclerViewAdapter = new HomeCardRecyclerViewAdapter(newReleasesList, new HomeCardRecyclerViewAdapter.OnItemClickListener() {

            //Click su elemento lista "Nuove Uscite"
            @Override
            public void onItemClick(TrackContainer trackContainer) {
                Log.d("Album", trackContainer.getName());

                //servirà per implementazione api (per ora prendiamo la tracklist del primo album di new releases da tracklist.json)
                List<Track> trackList = getTrackList(trackContainer.getId());

                Log.d("Tracklist: ", "");
                for(Track t : trackList){
                    Log.d("", t.getName());
                }
            }
        });


        HomeCardRecyclerViewAdapter playlistRecyclerViewAdapter = new HomeCardRecyclerViewAdapter(featuredPlaylistsList, new HomeCardRecyclerViewAdapter.OnItemClickListener() {

            //Click su elemento lista "Featured"
            @Override
            public void onItemClick(TrackContainer trackContainer) {
                Log.d("Playlist", trackContainer.getName());
            }
        });

        newReleasesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),  1,
                GridLayoutManager.HORIZONTAL, false));
        newReleasesRecyclerView.setAdapter(homeCardRecyclerViewAdapter);

        featuredPlaylistsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),  1,
                GridLayoutManager.HORIZONTAL, false));
        featuredPlaylistsRecyclerView.setAdapter(playlistRecyclerViewAdapter);




    }

    private List<Track> getTrackList(String trackContainerId) {
        InputStream fileInputStream = null;
        JsonReader jsonReader = null;
        try {
            fileInputStream = getActivity().getAssets().open("tracklist.json");
            jsonReader = new JsonReader(new InputStreamReader(fileInputStream, "UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            TrackListResponse response = new Gson().fromJson(bufferedReader, TrackListResponse.class);
            jsonReader.close();
            return response.getTrackList();
        } catch (IOException e) {
            //e.printStackTrace();
        }

        return null;
    }

    private List<Album> getNewReleases() throws IOException {
        InputStream fileInputStream = null;
        JsonReader jsonReader = null;
        try {
            fileInputStream = getActivity().getAssets().open("newreleases.json");
            jsonReader = new JsonReader(new InputStreamReader(fileInputStream, "UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            NewReleaseResponse response = new Gson().fromJson(bufferedReader, NewReleaseResponse.class);
            jsonReader.close();
            return response.getAlbumList();
        } catch (IOException e) {
            //e.printStackTrace();
        }

        return null;

    }

    private List<Playlist> getFeaturedPlaylists() throws IOException {
        InputStream fileInputStream = null;
        JsonReader jsonReader = null;
        try {
            fileInputStream = getActivity().getAssets().open("featured.json");
            jsonReader = new JsonReader(new InputStreamReader(fileInputStream, "UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            FeaturedResponse response = new Gson().fromJson(bufferedReader, FeaturedResponse.class);
            Log.d("PLAYLIST RESPONSE: ", response.getPlaylistWrapper().getPlaylistList().get(0).getName());
            featuredMessage = response.getMessage();
            jsonReader.close();
            return response.getPlaylistWrapper().getPlaylistList();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return null;
    }

    private void loadAlbumImages(List<Album> albumList){

        for(Album a : albumList){
            for(GenericImage i : a.getImgList()){
                i.setImg(Glide.with(this.getContext()).load(i.getImgUrl()));
            }
        }
    }

    private void loadPlaylistImages(List<Playlist> playlistList){

        for(Playlist p : playlistList){
            for(GenericImage i : p.getImgList()){
                i.setImg(Glide.with(this.getContext()).load(i.getImgUrl()));
            }
        }
    }
}
