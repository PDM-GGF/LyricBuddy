package com.progettopdm.lyricbuddy.repository;

import androidx.lifecycle.MutableLiveData;

import com.progettopdm.lyricbuddy.model.response.FeaturedResponse;
import com.progettopdm.lyricbuddy.model.response.NewReleaseResponse;
import com.progettopdm.lyricbuddy.model.response.TrackListResponse;

public interface ISpotifyRepository {
    MutableLiveData<NewReleaseResponse> fetchNewReleases(String token);
    MutableLiveData<TrackListResponse> fetchAlbumTrackList(String albumId, String token);
    MutableLiveData<FeaturedResponse> fetchFeaturedPlaylists(String token);
}
