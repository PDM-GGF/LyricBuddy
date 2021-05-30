package com.progettopdm.lyricbuddy.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.model.TrackContainer;

import java.util.List;

public class HomeCardRecyclerViewAdapter extends RecyclerView.Adapter<HomeCardRecyclerViewAdapter.NewReleasesViewHolder> {

    private List<? extends TrackContainer> tcList;
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(TrackContainer trackContainer);
    }

    public HomeCardRecyclerViewAdapter(List<? extends TrackContainer> tcList, OnItemClickListener onItemClickListener) {
        this.tcList = tcList;
        this.onItemClickListener = onItemClickListener;
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
        holder.bind(tcList.get(position));
    }

    @Override
    public int getItemCount() {
        return tcList.size();
    }

    public class NewReleasesViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameTextView;
        private final ImageView tcImageView;


        public NewReleasesViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tc_name);
            tcImageView = itemView.findViewById(R.id.tc_cover);
        }

        public void bind(TrackContainer trackContainer) {
            nameTextView.setText(trackContainer.getName());
            trackContainer.getImgList().get(0).getImg().into(tcImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    onItemClickListener.onItemClick(trackContainer);
                }
            });
        }
    }
}
