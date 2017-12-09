package com.example.admin.newsappfridayapp.homeActivity;

import android.view.View;

import com.example.admin.newsappfridayapp.data.Article;
import com.example.admin.newsappfridayapp.util.BasePresenter;
import com.example.admin.newsappfridayapp.util.BaseView;

import java.util.List;

/**
 * Created by  Admin on 12/8/2017.
 */

 interface NewsArticleContract {
    interface View extends BaseView{
       void setAticale(List<Article> articleList);
       void showProgress(String progress);
    }
    interface  Presenter extends BasePresenter<View>{
        void getNewsArtical();
    }
}
