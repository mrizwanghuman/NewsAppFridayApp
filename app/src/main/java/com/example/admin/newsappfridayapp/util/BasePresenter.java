package com.example.admin.newsappfridayapp.util;



/**
 * Created by  Admin on 12/8/2017.
 */

public interface BasePresenter<V extends BaseView> {
   void  attacheView(V view);
   void detachView();

}
