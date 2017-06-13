package com.jeongho.jogank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);

        final List<Meizi> list = new LinkedList<>();

        //        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        Gson gson = new Gson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        GankApi gankApi = retrofit.create(GankApi.class);

        Observable<ResponseBody> fuli1 = gankApi.getFuli(10, 1);
        fuli1.subscribeOn(Schedulers.io())

                .map(new Function<ResponseBody, List<Meizi>>() {
                    @Override
                    public List<Meizi> apply(ResponseBody responseBody) throws Exception {
                        System.out.println("map:" + Thread.currentThread().getName());
                        Fuli fuli = null;
                        try {
                            fuli = JSON.parseObject(responseBody.string(), Fuli.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        System.out.println(fuli.getResults().size());
                        return fuli.getResults();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Meizi>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Meizi> value) {
                System.out.println("onNext:" + Thread.currentThread().getName());
                list.clear();
                list.addAll(value);
                HeaderRecyclerAdapter<Meizi> adpater = new HeaderRecyclerAdapter<Meizi>(
                        MainActivity.this, list) {
                    @Override
                    void convertViewHolder(BaseViewHolder baseViewHolder, Meizi data, int itemType) {
                        Glide.with(MainActivity.this).load(data.getUrl()).into((ImageView) baseViewHolder.getView(R.id.iv));
                    }

                    @Override
                    void convertHeaderHolder(BaseViewHolder baseViewHolder, Meizi data, int itemType) {
                        Glide.with(MainActivity.this).load(data.getUrl()).into((ImageView) baseViewHolder.getView(R.id.iv));
                        ((TextView)baseViewHolder.getView(R.id.tv)).setText("有点困");
                    }
                };
                mRecyclerView.setAdapter(adpater);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
