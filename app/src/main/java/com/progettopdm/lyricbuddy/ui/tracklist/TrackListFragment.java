package com.progettopdm.lyricbuddy.ui.tracklist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.model.TrackContainer;
import com.progettopdm.lyricbuddy.ui.home.HomeCardRecyclerViewAdapter;
import com.progettopdm.lyricbuddy.ui.home.HomeViewModel;
import com.progettopdm.lyricbuddy.ui.home.HomeViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TrackListFragment extends Fragment {

    TrackListRecyclerViewAdapter trackListAdapter;


    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tracklist, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {


        HomeViewModel viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        TrackContainer tc = viewModel.getmClickedTrackContainer();

        TextView tlName = view.findViewById(R.id.tracklist_name);
        TextView tlDescription = view.findViewById(R.id.tracklist_description);
        ImageView tlImage = view.findViewById(R.id.tracklist_img);

        tlName.setText(tc.getName());
        tlDescription.setText(tc.getDescription());
        tc.getImgList().get(0).getImg().into(tlImage);

        RecyclerView newReleasesRecyclerView = view.findViewById(R.id.tracklist_recycler_view);

        trackListAdapter = new TrackListRecyclerViewAdapter(tc.getTrackList(), new TrackListRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Track track) {
                Log.d("CLICKED", String.valueOf(track));
            }
        });
        newReleasesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newReleasesRecyclerView.setAdapter(trackListAdapter);
    }

}
