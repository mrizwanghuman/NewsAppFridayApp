package com.example.admin.newsappfridayapp.homeActivity;

import android.util.Log;

import com.example.admin.newsappfridayapp.data.Article;
import com.example.admin.newsappfridayapp.data.NewsHeadlineData;
import com.example.admin.newsappfridayapp.remote.RemoteConnection;
import com.example.admin.newsappfridayapp.util.NewsArticleLlistRCAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by  Admin on 12/8/2017.
 */

public class HomePresenter implements NewsArticleContract.Presenter {
    List<Article> articleList= new ArrayList<>();
    NewsArticleContract.View view;
    public static final String TAG ="Presenter";
    @Override
    public void attacheView(NewsArticleContract.View view) {
this.view = view;
    }

    @Override
    public void detachView() {
this.view=null;
    }

    @Override
    public void getNewsArtical() {

        RemoteConnection.getTopNewsArticles().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<NewsHeadlineData>() {
            @Override
            public void onSubscribe(Disposable d) {
                view.showProgress("Downloading News..");
                Log.d(TAG, "onSubscribe: "+d.toString());
            }

            @Override
            public void onNext(NewsHeadlineData newsHeadlineData) {
                Log.d(TAG, "onNext: "+newsHeadlineData.getArticles().get(0).getTitle());
                articleList.addAll(newsHeadlineData.getArticles());


            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
                view.showError(e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
                view.setAticale(articleList);
            }
        });
    }
}
