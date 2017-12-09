package com.example.admin.newsappfridayapp.homeActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.admin.newsappfridayapp.R;
import com.example.admin.newsappfridayapp.data.Article;
import com.example.admin.newsappfridayapp.util.NewsArticleLlistRCAdapter;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements NewsArticleContract.View{

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 10;
    private static final String TAG = "HomeActivity";
    RecyclerView recyclerView;
    private NewsArticleLlistRCAdapter newsArticleLlistRCAdapter;
    private List<Article> articleList;
    HomePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rcNewsHeadLine);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter = new HomePresenter();
        getLocationPermissionRunTime();
        presenter.attacheView(this);
        presenter.getNewsArtical();

    }

    //Getting location permission run time
    public void getLocationPermissionRunTime() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Log.d(TAG, "onRequestPermissionsResult: granted");
                } else {
                    Log.d(TAG, "onRequestPermissionsResult: denied");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void setAticale(List<Article> articleList) {
        newsArticleLlistRCAdapter = new NewsArticleLlistRCAdapter(articleList, this);
        recyclerView.setAdapter(newsArticleLlistRCAdapter);
        Log.d(TAG, "setAticale: " + articleList.get(0).getTitle());
    }

    @Override
    public void showProgress(String progress) {
        Toast.makeText(this, progress, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showError(String progress) {
        Toast.makeText(this, progress, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "showError: " + progress);
        Log.d(TAG, "showError: error ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


}
