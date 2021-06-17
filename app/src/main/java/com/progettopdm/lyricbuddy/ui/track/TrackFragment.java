package com.progettopdm.lyricbuddy.ui.track;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.database.TrackDao;
import com.progettopdm.lyricbuddy.database.TrackRoomDatabase;
import com.progettopdm.lyricbuddy.model.Artist;
import com.progettopdm.lyricbuddy.model.GenericImage;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.repository.DatabaseRepository;
import com.progettopdm.lyricbuddy.repository.IDatabaseRepository;
import com.progettopdm.lyricbuddy.repository.MxmLyricsCallback;
import com.progettopdm.lyricbuddy.repository.MxmLyricsRepository;
import com.progettopdm.lyricbuddy.repository.MxmMatcherCallback;
import com.progettopdm.lyricbuddy.repository.MxmMatcherRepository;
import com.progettopdm.lyricbuddy.ui.favorites.FavoritesViewModel;
import com.progettopdm.lyricbuddy.ui.favorites.FavoritesViewModelFactory;
import com.progettopdm.lyricbuddy.ui.tracklist.TrackListViewModel;
import com.progettopdm.lyricbuddy.utils.ServiceLocator;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TrackFragment extends Fragment implements MxmLyricsCallback, MxmMatcherCallback {
    private TrackDao tTrackDao;
    private MxmLyricsRepository mxmLyricsRepository;
    private MxmMatcherRepository mxmMatcherRepository;
    private IDatabaseRepository iDatabaseRepository;

    private View root;
    private TrackListViewModel trackListViewModel;
    private FavoritesViewModel favoritesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_track, container, false);


        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Toolbar toolbar = getActivity().findViewById(R.id.main_toolbar);
        toolbar.setVisibility(View.INVISIBLE);

        //BackButton
        final ImageView back = root.findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        CheckBox heart = root.findViewById(R.id.heart_checkbox);

        trackListViewModel = new ViewModelProvider(requireActivity()).get(TrackListViewModel.class);

        TrackRoomDatabase db = ServiceLocator.getInstance().getTrackDao(requireActivity().getApplication());
        this.tTrackDao = db.TrackDao();

        iDatabaseRepository = new DatabaseRepository(getActivity().getApplication());

        favoritesViewModel =
                new ViewModelProvider(requireActivity(),
                        new FavoritesViewModelFactory(requireActivity().getApplication(), iDatabaseRepository))
                        .get(FavoritesViewModel.class);


        //Controlla se la traccia è tra i preferiti
        favoritesViewModel.getmTracks().observe(getViewLifecycleOwner(), new Observer<List<Track>>() {
            @Override
            public void onChanged(List<Track> tracks) {
                for (Track t : tracks) {
                    if (t.getTrackId().equals(trackListViewModel.getmClickedTrack().getTrackId())) {
                        heart.setChecked(true);
                    }
                }

            }
        });

        String q_track = trackListViewModel.getmClickedTrack().getName();
        String q_artist = trackListViewModel.getmClickedTrack().getArtists().get(0).getName();

        TextView trackName = root.findViewById(R.id.track_name);
        TextView trackArtist = root.findViewById(R.id.track_artist);

        trackName.setText(q_track);

        List<Artist> artists = trackListViewModel.getmClickedTrack().getArtists();

        //Generate string "artists", this part puts the artists separated by a comma if needed
        StringBuilder string_artists = new StringBuilder();
        for (Artist a : artists) {
            if (a.equals(artists.get(0)))
                string_artists.append(a.getName());
            else
                string_artists.append(", ").append(a.getName());
        }
        trackArtist.setText(string_artists.toString());

        ImageView trackImage = root.findViewById(R.id.track_img);
        ImageView infoImage = root.findViewById(R.id.album_artwork);
        GenericImage track_artwork;

        //Check if the album is set
        if (trackListViewModel.getmClickedTrack().getAlbum() != null) {
            track_artwork = trackListViewModel.getmClickedTrack().getAlbum().getImgList().get(0);
        } else {
            track_artwork = trackListViewModel.getmClickedImage();
        }

        Glide.with(view).load(track_artwork.getImgUrl()).into(trackImage);
        Glide.with(view).load(track_artwork.getImgUrl()).into(infoImage);

        TextView albumInfo = root.findViewById(R.id.album_info);
        TextView albumReleaseDate = root.findViewById(R.id.album_release_date);
        StringBuilder string_info = new StringBuilder();
        if (trackListViewModel.getmClickedTrack().getAlbum() != null) {
            string_info.append(trackListViewModel.getmClickedTrack().getAlbum().getName());
            string_info.append("\ndi ").append(trackListViewModel.getmClickedTrack().getArtists().get(0).getName());
            albumInfo.setText(string_info.toString());
            albumReleaseDate.setText("Released " + trackListViewModel.getmClickedTrack().getAlbum().getRelease_date());
        }

        /*if (trackListViewModel.getmClickedTrack().getPopularity() != null) {
            string_info.append("\nPopularity: ").append(trackListViewModel.getmClickedTrack().getPopularity());
        }*/


        if (trackListViewModel.getmClickedTrack().getDuration_ms() != 0) {
            String duration =
                    TimeUnit.MILLISECONDS.toMinutes(trackListViewModel.getmClickedTrack().getDuration_ms())
                            + ":" +
                            String.valueOf(TimeUnit.MILLISECONDS.toSeconds(trackListViewModel.getmClickedTrack().getDuration_ms()) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(trackListViewModel.getmClickedTrack().getDuration_ms())));
            TextView lyricsTitle = root.findViewById(R.id.lyrics_title);
            lyricsTitle.setText("[duration " + duration + "]");
        }

        super.onActivityCreated(savedInstanceState);

        mxmMatcherRepository = new MxmMatcherRepository(this);
        mxmMatcherRepository.fetchTrackId(q_track, q_artist);


        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (heart.isChecked()) {

                    favoritesViewModel.getmTracks().observe(getViewLifecycleOwner(), new Observer<List<Track>>() {
                        @Override
                        public void onChanged(List<Track> tracks) {
                            boolean isInDb = false;
                            for (Track t : tracks) {
                                if (t.getTrackId().equals(trackListViewModel.getmClickedTrack().getTrackId())) {
                                    isInDb = true;
                                }
                            }
                            if (!isInDb) {
                                saveDataInDatabase(trackListViewModel.getmClickedTrack());
                                Log.d("FAVORITE", "CREATED");

                            }

                        }
                    });

                } else {

                    deleteDataInDatabase(trackListViewModel.getmClickedTrack());
                    Log.d("FAVORITE", "DELETED");
                    heart.setChecked(false);

                }
            }
        });

    }

    private void lyricsFailed() {
        TextView lyricsTitle = root.findViewById(R.id.lyrics_title);
        lyricsTitle.setText(R.string.lyircs_fail);
    }

    private void saveDataInDatabase(Track track) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tTrackDao.insertTrack(track);
            }
        };
        new Thread(runnable).start();
    }

    private void deleteDataInDatabase(Track track) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tTrackDao.deleteTrack(track);
            }
        };
        new Thread(runnable).start();
    }


    @Override
    public void onLyricsGet(String lyrics) {
        try {
            lyrics = lyrics.substring(0, lyrics.indexOf("*"));
            lyrics = lyrics.trim();
            TextView trackLyrics = root.findViewById(R.id.track_lyrics);
            //trackLyrics.setMovementMethod(new ScrollingMovementMethod());
            trackLyrics.setText(lyrics);
        } catch (java.lang.StringIndexOutOfBoundsException e) {
            lyricsFailed();
        }
    }

    @Override
    public void onLyricsFailure(String msg) {
        lyricsFailed();
    }

    @Override
    public void onIdGet(int id) {
        mxmLyricsRepository = new MxmLyricsRepository(this);
        mxmLyricsRepository.fetchLyrics(id);
    }

    @Override
    public void onMatcherFailure(String msg) {
        lyricsFailed();
    }

}