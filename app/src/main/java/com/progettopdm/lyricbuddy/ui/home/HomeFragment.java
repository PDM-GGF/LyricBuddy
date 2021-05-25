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
import com.progettopdm.lyricbuddy.model.response.FeaturedResponse;
import com.progettopdm.lyricbuddy.model.response.NewReleaseResponse;
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
        newReleasesList = getNewReleases();
        featuredPlaylistsList = getFeaturedPlaylists();
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

        AlbumRecyclerViewAdapter albumRecyclerViewAdapter = new AlbumRecyclerViewAdapter(newReleasesList);
        PlaylistRecyclerViewAdapter playlistRecyclerViewAdapter = new PlaylistRecyclerViewAdapter(featuredPlaylistsList);

        newReleasesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),  1,
                GridLayoutManager.HORIZONTAL, false));
        newReleasesRecyclerView.setAdapter(albumRecyclerViewAdapter);

        featuredPlaylistsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),  1,
                GridLayoutManager.HORIZONTAL, false));
        featuredPlaylistsRecyclerView.setAdapter(playlistRecyclerViewAdapter);




    }

    private List<Album> getNewReleases() {
        InputStream fileInputStream = null;
        JsonReader jsonReader = null;
        try {
            fileInputStream = getActivity().getAssets().open("newreleases.json");
            jsonReader = new JsonReader(new InputStreamReader(fileInputStream, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        NewReleaseResponse response = new Gson().fromJson(bufferedReader, NewReleaseResponse.class);

        return response.getAlbumList();
    }

    private List<Playlist> getFeaturedPlaylists() {
        InputStream fileInputStream = null;
        JsonReader jsonReader = null;
        try {
            fileInputStream = getActivity().getAssets().open("featured.json");
            jsonReader = new JsonReader(new InputStreamReader(fileInputStream, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        FeaturedResponse response = new Gson().fromJson(bufferedReader, FeaturedResponse.class);

        Log.d("PLAYLIST RESPONSE: ", response.getPlaylistWrapper().getPlaylistList().get(0).getName());

        featuredMessage = response.getMessage();

        return response.getPlaylistWrapper().getPlaylistList();
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
