package com.jeongho.jogank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);

        List<String>  list = new LinkedList<>();
        for (int i = 0; i < 20; i++){
            list.add("" + i);
        }


        HeaderRecyclerAdapter<String> adpater = new HeaderRecyclerAdapter<>(MainActivity.this
        ,list);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        mRecyclerView.setAdapter(adpater);

        Gson gson = new Gson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GankApi gankApi = retrofit.create(GankApi.class);

        Call<ResponseBody> fuli = gankApi.getFuli(5, 1);
        fuli.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });



    }
}
