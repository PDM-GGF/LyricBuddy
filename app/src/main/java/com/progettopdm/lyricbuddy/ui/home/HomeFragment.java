package com.progettopdm.lyricbuddy.ui.home;

import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
import com.progettopdm.lyricbuddy.model.AlbumImg;
import com.progettopdm.lyricbuddy.model.NewReleaseResponse;
import com.progettopdm.lyricbuddy.repository.CCAuthRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    List<Album> albumList;

    CCAuthRepository ccAuthRepository = new CCAuthRepository();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ccAuthRepository.authorize();
        albumList = getNewReleases();
        loadAlbumImages(albumList);

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

        RecyclerView newReleasesList = view.findViewById(R.id.new_releases_list);

        NewReleasesAdapter newReleasesAdapter = new NewReleasesAdapter(albumList);

        newReleasesList.setLayoutManager(new GridLayoutManager(getContext(),  1,
                GridLayoutManager.HORIZONTAL, false));
        newReleasesList.setAdapter(newReleasesAdapter);



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

    private void loadAlbumImages(List<Album> albumList){

        for(Album a : albumList){
            for(AlbumImg i : a.getAlbumImgList()){
                i.setAlbumImg(Glide.with(this.getContext()).load(i.getImgUrl()));
            }
        }
    }
}
