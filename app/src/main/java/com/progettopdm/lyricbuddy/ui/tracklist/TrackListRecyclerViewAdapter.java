package com.progettopdm.lyricbuddy.ui.tracklist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.model.Track;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TrackListRecyclerViewAdapter extends RecyclerView.Adapter<TrackListRecyclerViewAdapter.TrackListViewHolder> {

    private List<Track> trackList;
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Track track);
    }
    public TrackListRecyclerViewAdapter(List<Track> trackList){
        this.trackList = trackList;
    }

    public TrackListRecyclerViewAdapter(List<Track> trackList, TrackListRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        this.trackList = trackList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public TrackListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tracklist_item, parent, false);
        return new TrackListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TrackListViewHolder holder, int position) {
        holder.bind(trackList.get(position));
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    public class TrackListViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameTextView;
        private final TextView artistTextView;
        private final ImageView trackImageView;

        public TrackListViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.track_name);
            artistTextView = itemView.findViewById(R.id.track_artist);
            trackImageView = itemView.findViewById(R.id.track_img);
        }

        public void bind(Track track) {
            nameTextView.setText(track.getName());
            if(track.getArtists() != null){
                artistTextView.setText(track.getArtists().get(0).getName());
            }

            if(track.getAlbum() != null) {
                if(track.getAlbum().getImgList() != null)
                    Glide.with(itemView).load(track.getAlbum().getImgList().get(0).getImgUrl()).into(trackImageView);

            } else {

            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(track);
                }
            });
        }

    }


}
