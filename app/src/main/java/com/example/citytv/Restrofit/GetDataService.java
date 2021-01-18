package com.example.citytv.Restrofit;

import com.example.citytv.Model.PlaylistModel.Playlist;
import com.example.citytv.Model.VideoStats.VideoDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("search")
    Call<VideoDetails> getVideoData(
            @Query("part") String part,
            @Query("channelId") String channelId,
            @Query("key") String key,
            @Query("order") String order,
            @Query("maxResults") String maxResults
            );

    @GET("playlists")
    Call<Playlist.Playlists> getPlayLists(
     @Query("part") String part,
     @Query("key") String key,
     @Query("channelId") String channelId
    );
}
