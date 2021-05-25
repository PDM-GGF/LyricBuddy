package com.progettopdm.lyricbuddy.ui.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.model.Album;
import com.progettopdm.lyricbuddy.model.AlbumImg;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class NewReleasesAdapter extends RecyclerView.Adapter<NewReleasesAdapter.NewReleasesViewHolder> {

    private List<Album> albumList;

    public NewReleasesAdapter(List<Album> albumList) {
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public NewReleasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_item, parent, false);
        return new NewReleasesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewReleasesViewHolder holder, int position) {
        try {
            holder.bind(albumList.get(position));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class NewReleasesViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleTextView;
        private final ImageView albumImageView;


        public NewReleasesViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.album_title);
            albumImageView = itemView.findViewById(R.id.album_cover);
        }

        public void bind(Album album) throws IOException {
            titleTextView.setText(album.getTitle());
            album.getAlbumImgList().get(0).getAlbumImg().into(albumImageView);
        }
    }
}
