package com.progettopdm.lyricbuddy.repository;

import androidx.lifecycle.MutableLiveData;

import com.progettopdm.lyricbuddy.model.response.FeaturedResponse;
import com.progettopdm.lyricbuddy.model.response.NewReleaseResponse;
import com.progettopdm.lyricbuddy.model.response.AlbumTrackListResponse;
import com.progettopdm.lyricbuddy.model.response.PlaylistTrackListResponse;
import com.progettopdm.lyricbuddy.model.response.SearchTracksResponse;

public interface ISpotifyRepository {
    MutableLiveData<NewReleaseResponse> fetchNewReleases(String token);
    MutableLiveData<AlbumTrackListResponse> fetchAlbumTrackList(String albumId, String token);
    MutableLiveData<FeaturedResponse> fetchFeaturedPlaylists(String token);
    MutableLiveData<PlaylistTrackListResponse> fetchPlaylistTracklist(String playlistId, String token);
    MutableLiveData<SearchTracksResponse> fetchSearchedTracks(String query, String token);
}
