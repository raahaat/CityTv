package com.example.citytv.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.citytv.Activity.MainActivity;
import com.example.citytv.Model.PlaylistModel.Item;
import com.example.citytv.Model.PlaylistModel.Playlist;
import com.example.citytv.Model.VideoStats.VideoDetails;
import com.example.citytv.Network.YoutubeAPI;
import com.example.citytv.R;
import com.example.citytv.Restrofit.GetDataService;
import com.example.citytv.Restrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.volley.VolleyLog.TAG;

public class PlaylistDetailsAdapter extends RecyclerView.Adapter<PlaylistDetailsAdapter.PlaylistDetailsViewHolder> {
    private Context context;
    private List<Item> playlistsdetailsList;
    private List<com.example.citytv.Model.VideoStats.Item> itemList1;

    private final String TAG= MainActivity.class.getSimpleName();
    private VideoDetailsAdapter videoDetailsAdapter;




     public PlaylistDetailsAdapter(Context context,List<Item> playlistsdetailsList)
    {
        this.context = context;
        this.playlistsdetailsList =playlistsdetailsList;
    }


    public PlaylistDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemList1=new ArrayList<>();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_listitem,parent,false);


        return new PlaylistDetailsViewHolder(view);


    }




    @Override
    public void onBindViewHolder(@NonNull PlaylistDetailsViewHolder holder, int position) {
       holder.Playlistname.setText(playlistsdetailsList.get(position).getSnippet().getTitle());
        GetDataService dataService= RetrofitInstance.getRetrofit().create(GetDataService.class);
        Call<VideoDetails> videoDetailsRequest =dataService.
                getVideoData("snippet","UC142mcVG2Pv2rY6t0NHMI_w","AIzaSyB9aq7ck07ous8KnINWUx0QDpWISmOdu4U","date", "50");
        videoDetailsRequest.enqueue(new Callback<VideoDetails>() {
            @Override
            public void onResponse(Call<VideoDetails> call, Response<VideoDetails> response) {
                if(response.isSuccessful()){
                    if(response.body() !=null){
                        Log.e(TAG,"Response Successfull");
                        setUpRecylerview1(holder.recyclerView2,response.body().getItems());

                    }
                }

            }



            @Override
            public void onFailure(Call<VideoDetails> call, Throwable t) {
                Log.e(TAG.concat(" API Request Failled "),t.getMessage());
            }
        });
    }


    @Override
    public int getItemCount() {
        return playlistsdetailsList.size();
    }

    public class PlaylistDetailsViewHolder extends RecyclerView.ViewHolder {
         private TextView Playlistname;
         private RecyclerView recyclerView2;

        public PlaylistDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            Playlistname = itemView.findViewById(R.id.playListText);
            recyclerView2=itemView.findViewById(R.id.recycler_view1);


        }
    }


    private void setUpRecylerview1(RecyclerView recyclerView2,List<com.example.citytv.Model.VideoStats.Item> items) {
        videoDetailsAdapter = new VideoDetailsAdapter(context,items);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(videoDetailsAdapter);


    }

}
