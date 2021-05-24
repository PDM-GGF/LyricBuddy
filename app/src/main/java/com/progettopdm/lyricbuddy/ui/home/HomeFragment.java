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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.model.Album;
import com.progettopdm.lyricbuddy.model.NewReleaseResponse;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.repository.CCAuthRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    CCAuthRepository ccAuthRepository = new CCAuthRepository();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ccAuthRepository.authorize();
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

        List<Album> albumList = getNewReleases();

        Log.d("Lista album: ", albumList.get(1).getTitle());

        RecyclerView newReleasesList = view.findViewById(R.id.new_releases_list);

        NewReleasesAdapter newReleasesAdapter = new NewReleasesAdapter(albumList);

        newReleasesList.setLayoutManager(new LinearLayoutManager(getContext()));
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

        Log.d("Lista album: ", response.toString());



        return response.getAlbumList();
    }
}