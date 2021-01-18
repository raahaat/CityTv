package com.example.citytv.Restrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit=null;
   private static String Base_Url="https://www.googleapis.com/youtube/v3/";

    public static Retrofit getRetrofit() {
        if(retrofit == null)
        {

            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
            okHttpClient.readTimeout(50, TimeUnit.SECONDS);
            okHttpClient.connectTimeout(50, TimeUnit.SECONDS);

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient.addInterceptor(logging);

            Gson gson = new GsonBuilder()
//                .excludeFieldsWithoutExposeAnnotation()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .client(okHttpClient.build())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

//         retrofit =new Retrofit.Builder()
//                 .addConverterFactory(GsonConverterFactory.create())
//                 .baseUrl(Base_Url)
//                 .build();
        }
     return retrofit;
    }

}
