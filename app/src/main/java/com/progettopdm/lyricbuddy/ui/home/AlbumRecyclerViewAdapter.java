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
import com.progettopdm.lyricbuddy.model.TrackContainer;

import java.io.IOException;
import java.util.List;

public class AlbumRecyclerViewAdapter extends RecyclerView.Adapter<AlbumRecyclerViewAdapter.NewReleasesViewHolder> {

    private List<Album> albumList;

    public AlbumRecyclerViewAdapter(List<Album> albumList) {
        this.albumList = albumList;
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
            holder.bind(albumList.get(position));
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class NewReleasesViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameTextView;
        private final ImageView albumImageView;


        public NewReleasesViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.album_name);
            albumImageView = itemView.findViewById(R.id.album_cover);
        }

        public void bind(TrackContainer trackContainer) throws IOException {
            nameTextView.setText(trackContainer.getName());
            trackContainer.getImgList().get(0).getImg().into(albumImageView);
        }
    }
}
