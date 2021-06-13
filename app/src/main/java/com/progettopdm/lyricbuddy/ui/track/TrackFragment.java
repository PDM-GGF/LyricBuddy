package com.progettopdm.lyricbuddy.ui.track;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.model.Artist;
import com.progettopdm.lyricbuddy.model.GenericImage;
import com.progettopdm.lyricbuddy.repository.MxmLyricsCallback;
import com.progettopdm.lyricbuddy.repository.MxmLyricsRepository;
import com.progettopdm.lyricbuddy.repository.MxmMatcherCallback;
import com.progettopdm.lyricbuddy.repository.MxmMatcherRepository;
import com.progettopdm.lyricbuddy.ui.tracklist.TrackListViewModel;

import java.util.List;

public class TrackFragment extends Fragment implements MxmLyricsCallback, MxmMatcherCallback {

    private MxmLyricsRepository mxmLyricsRepository;
    private MxmMatcherRepository mxmMatcherRepository;

    private View root;
    private TrackListViewModel trackListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_track, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        trackListViewModel = new ViewModelProvider(requireActivity()).get(TrackListViewModel.class);


        String q_track = trackListViewModel.getmClickedTrack().getName();
        String q_artist = trackListViewModel.getmClickedTrack().getArtists().get(0).getName();


        TextView trackName = root.findViewById(R.id.track_name);
        TextView trackArtist = root.findViewById(R.id.track_artist);

        trackName.setText(q_track);

        List<Artist> artists = trackListViewModel.getmClickedTrack().getArtists();

        //Generate string "artists", this part puts the artists separated by a comma if needed
        StringBuilder string_artists = new StringBuilder();
        for(Artist a : artists){
            if(a.equals(artists.get(0)))
                string_artists.append(a.getName());
            else
                string_artists.append(", ").append(a.getName());
        }

        trackArtist.setText(string_artists.toString());

        //Check if the album is set
        if (trackListViewModel.getmClickedTrack().getAlbum() != null) {
            GenericImage track_artwork = trackListViewModel.getmClickedTrack().getAlbum().getImgList().get(0);
            ImageView trackImage = root.findViewById(R.id.track_img);
            Glide.with(view).load(track_artwork.getImgUrl()).into(trackImage);
        } else {
            GenericImage track_artwork = trackListViewModel.getmClickedImage();
            ImageView trackImage = root.findViewById(R.id.track_img);
            Glide.with(view).load(track_artwork.getImgUrl()).into(trackImage);
        }

        super.onActivityCreated(savedInstanceState);

        mxmMatcherRepository = new MxmMatcherRepository(this);
        mxmMatcherRepository.fetchTrackId(q_track, q_artist);

    }

    @Override
    public void onLyricsGet(String lyrics) {
        try {
            lyrics = lyrics.substring(0, lyrics.indexOf("*"));
        } catch (java.lang.StringIndexOutOfBoundsException e) {
            lyrics = "Scusa, probabilmente questi lyrics non sono ancora disponibili :( ";
        }
        TextView trackLyrics = root.findViewById(R.id.track_lyrics);
        trackLyrics.setMovementMethod(new ScrollingMovementMethod());
        trackLyrics.setText(lyrics);
    }

    @Override
    public void onLyricsFailure(String msg) {
        TextView trackLyrics = root.findViewById(R.id.track_lyrics);
        trackLyrics.setText(msg);
    }

    @Override
    public void onIdGet(int id) {
        mxmLyricsRepository = new MxmLyricsRepository(this);
        mxmLyricsRepository.fetchLyrics(id);
    }

    @Override
    public void onMatcherFailure(String msg) {
        TextView trackLyrics = root.findViewById(R.id.track_lyrics);
        trackLyrics.setText(R.string.matcher_fail);
    }

}