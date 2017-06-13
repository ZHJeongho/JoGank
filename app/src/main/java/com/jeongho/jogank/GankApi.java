package com.jeongho.jogank;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jeongho on 2017/6/8.
 */

public interface GankApi {
    @GET("day/{year}/{month}/{day}")
    Call<String> getDailyData(@Path("year") int year, @Path("year") int month, @Path("year") int day);

    @GET("random/data/{type}/{count}")
    Call<String> getRandomData(@Path("type") String type, @Path("count") int count);

//    @GET("random/data/{type}/{count}")
//    Call<String> getSortData(@Path("type") String type, @Path("count") int count);

    @GET("data/福利/{count}/{page}")
    Observable<ResponseBody> getFuli(@Path("count") int count, @Path("page") int page);

}
