package com.example.admin.newsappfridayapp.remote;

import com.example.admin.newsappfridayapp.data.NewsHeadlineData;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by  Admin on 12/8/2017.
 */

public class RemoteConnection {

    public static final String BASE_URL="https://newsapi.org/v2/";
    public static Retrofit create(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
    public static Observable<NewsHeadlineData> getTopNewsArticles(){
        Retrofit retrofit= create();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        return remoteService.getListOfNewsArticle();
    }
}
