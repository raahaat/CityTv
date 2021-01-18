package com.example.citytv.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.citytv.Adapter.PlaylistDetailsAdapter;
import com.example.citytv.Adapter.VideoDetailsAdapter;
import com.example.citytv.Model.PlaylistModel.Item;
import com.example.citytv.Model.PlaylistModel.Playlist;
import com.example.citytv.Model.VideoStats.VideoDetails;
import com.example.citytv.R;
import com.example.citytv.Restrofit.GetDataService;
import com.example.citytv.Restrofit.RetrofitInstance;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView1,recyclerView2;

    private PlaylistDetailsAdapter playlistDetailsAdapter;
    private final String TAG= MainActivity.class.getSimpleName();
    private SliderLayout DsliderLayout;
    private List<Item> itemList;



    private Button button1;
    String[] url = new String[2];
    SliderLayout sliderLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Slider Image View
        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setScrollTimeInSec(1);
        setSliderViews();

        itemList =new ArrayList<>();

//
        recyclerView1=findViewById(R.id.recycler_view);





        getPlaylists();






   //Upper portion
     button1 =(Button) findViewById(R.id.Button1);
     url[0]="https://citytvbd.net/?fbclid=IwAR1sul6TLQG1tUUiMvK3TDWc1-1kABAfgcuyu9e7GmSSsCs8MUtvJsfJ6xY";
     button1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
          openActivity2();
         }
     });

    }





    private void getPlaylists() {
        GetDataService dataService= RetrofitInstance.getRetrofit().create(GetDataService.class);
        Call<Playlist.Playlists> playlistRequest=dataService.getPlayLists("snippet","AIzaSyB9aq7ck07ous8KnINWUx0QDpWISmOdu4U","UC142mcVG2Pv2rY6t0NHMI_w");
        playlistRequest.enqueue(new Callback<Playlist.Playlists>() {
            @Override
            public void onResponse(Call<Playlist.Playlists> call, Response<Playlist.Playlists> response) {
            if(response.isSuccessful()){
                if(response.body() != null){
                 Log.e(TAG,"Response Successfull");
                    Toast.makeText(MainActivity.this, "Loading, Please wait", Toast.LENGTH_LONG).show();
                setUpRecyclerView(response.body().getItems());
                }
                else{
                    Toast.makeText(MainActivity.this, "No Playlists Found", Toast.LENGTH_SHORT).show();

                }
            }

            }



            @Override
            public void onFailure(Call<Playlist.Playlists> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                Log.e(TAG.concat(" API Request Failled "),t.getMessage());
            }
        });
    }



    //recycler view1
    private void setUpRecyclerView(List<Item> items) {
        playlistDetailsAdapter =new PlaylistDetailsAdapter(MainActivity.this,items);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(MainActivity.this);
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView1.setAdapter(playlistDetailsAdapter);

    }

    //recycler view2



    //recycler view3


    public void openActivity2()
{
    Intent intent = new Intent(this,CityTvLive.class);
    intent.putExtra("links",url[0]);
    startActivity(intent);
}

public void setSliderViews()
{
        for(int i= 0; i<=3 ; i++)
        {
            DefaultSliderView sliderView =new DefaultSliderView(this);
            switch (i)
            {
                case 0:
                    sliderView.setImageDrawable(R.drawable.one);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.two);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.three);
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.four);
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
            sliderLayout.addSliderView(sliderView);
        }
}

}



