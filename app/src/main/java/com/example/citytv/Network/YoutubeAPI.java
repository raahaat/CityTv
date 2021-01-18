package com.example.citytv.Network;

import com.example.citytv.Model.VideoStats.VideoDetails;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class YoutubeAPI {

    //https://www.googleapis.com/youtube/v3/
    // search?
    // part=snippet
    // &channelId=UC142mcVG2Pv2rY6t0NHMI_w
    // &maxResults=50
    // &order=date
    // &key=AIzaSyAW0SG-E2trtkDTaPq_8MKlZAle53CGnhc
    public static final String BASE_URL="https://www.googleapis.com/youtube/v3/";
    public static final String KEY="&key=AIzaSyAW0SG-E2trtkDTaPq_8MKlZAle53CGnhc";
    public static final String sch ="search?";
    public static final String part ="part=snippet";
    public static final String chid ="&channelId=UC142mcVG2Pv2rY6t0NHMI_w";
    public static final String maxR ="&maxResults=50";
    public static final String order ="&order=date";

    public interface Homevideo{
        @GET
        Call<VideoDetails> getYT(@Url String url);
    }
       private static Homevideo homevideo = null;
    public static Homevideo getHomevideo(){
        if(homevideo == null){
            Retrofit retrofit =new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            homevideo =retrofit.create(Homevideo.class);
        }
        return homevideo;
    }
}
