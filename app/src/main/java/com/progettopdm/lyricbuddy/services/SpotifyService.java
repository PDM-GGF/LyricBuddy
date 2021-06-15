package com.progettopdm.lyricbuddy.services;

import com.progettopdm.lyricbuddy.model.response.FeaturedResponse;
import com.progettopdm.lyricbuddy.model.response.NewReleaseResponse;
import com.progettopdm.lyricbuddy.model.response.AlbumTrackListResponse;
import com.progettopdm.lyricbuddy.model.response.PlaylistTrackListResponse;
import com.progettopdm.lyricbuddy.model.response.SearchTracksResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SpotifyService {

    @GET("browse/new-releases")
    Call<NewReleaseResponse> getNewReleases(@Query("country") String country,
                                @Query("limit") int limit,
                                @Header("Authorization") String authToken);

    @GET("albums/{id}/tracks")
    Call<AlbumTrackListResponse> getAlbumTrackList(@Path("id") String albumId,
                                                   @Query("market") String market,
                                                   @Query("limit") int limit,
                                                   @Header("Authorization") String authToken);

    @GET("browse/featured-playlists")
    Call<FeaturedResponse> getFeaturedPlaylists(@Query("country") String country,
                                                @Query("limit") int limit,
                                                @Header("Authorization") String authToken);

    @GET("playlists/{playlist_id}/tracks")
    Call<PlaylistTrackListResponse> getPlaylistTrackList(@Path("playlist_id") String playlistId,
                                                      @Query("market") String market,
                                                      @Header("Authorization") String authToken);

    @GET("search")
    Call<SearchTracksResponse> getSearchedTracks(@Query("q") String query,
                                                 @Query("type") String type,
                                                 @Header("Authorization") String authToken);
}
