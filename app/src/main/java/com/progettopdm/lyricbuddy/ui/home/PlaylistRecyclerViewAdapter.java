package com.progettopdm.lyricbuddy.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.model.Album;
import com.progettopdm.lyricbuddy.model.Playlist;
import com.progettopdm.lyricbuddy.model.TrackContainer;

import java.io.IOException;
import java.util.List;

public class PlaylistRecyclerViewAdapter extends RecyclerView.Adapter<PlaylistRecyclerViewAdapter.NewReleasesViewHolder> {

    private List<Playlist> playlistList;

    public PlaylistRecyclerViewAdapter(List<Playlist> playlistList) {
        this.playlistList = playlistList;
    }

    @NonNull
    @Override
    public NewReleasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new NewReleasesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewReleasesViewHolder holder, int position) {
        try {
            holder.bind(playlistList.get(position));
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return playlistList.size();
    }

    public class NewReleasesViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameTextView;
        private final ImageView playlistImageView;


        public NewReleasesViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.album_name);
            playlistImageView = itemView.findViewById(R.id.album_cover);
        }

        public void bind(TrackContainer trackContainer) throws IOException {
            nameTextView.setText(trackContainer.getName());
            trackContainer.getImgList().get(0).getImg().into(playlistImageView);
        }
    }
}
