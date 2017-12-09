package com.example.admin.newsappfridayapp.remote;

import com.example.admin.newsappfridayapp.data.Article;
import com.example.admin.newsappfridayapp.data.NewsHeadlineData;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by  Admin on 12/8/2017.
 */

public interface RemoteService {

    @GET("top-headlines?sources=bbc-news&apiKey=0303f91d23734757a27ed7222ed817e6")
    Observable<NewsHeadlineData> getListOfNewsArticle();
}
