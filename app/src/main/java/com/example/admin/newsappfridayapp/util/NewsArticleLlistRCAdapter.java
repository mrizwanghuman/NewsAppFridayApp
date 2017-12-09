package com.example.admin.newsappfridayapp.util;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.newsappfridayapp.R;
import com.example.admin.newsappfridayapp.data.Article;
import com.example.admin.newsappfridayapp.data.database.DatabaseHelper;
import com.example.admin.newsappfridayapp.data.database.NewsHeadlineObject;

import java.util.List;

/**
 * Created by  Admin on 12/8/2017.
 */

public class NewsArticleLlistRCAdapter extends RecyclerView.Adapter<NewsArticleLlistRCAdapter.ViewHolder> {
    List<Article> articleList;
    private Context context;
    private DatabaseHelper databaseHelper;


    public NewsArticleLlistRCAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;
    }

    @Override
    public NewsArticleLlistRCAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_recylerview,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsArticleLlistRCAdapter.ViewHolder holder, final int position) {
    final String newsArticleTitle = articleList.get(position).getTitle();
    final String newArticlePublishDate = articleList.get(position).getPublishedAt();
        databaseHelper = new DatabaseHelper(context);
    holder.tvArticleTile.setText(newsArticleTitle);
    holder.tvArticlePublishDate.setText(newArticlePublishDate);
        Cursor cursor = databaseHelper.getNewsHeadLin(newsArticleTitle);
        if (cursor.getCount()==1){
            holder.btnFavrt.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        Log.d("Query", "onBindViewHolder: "+cursor.getCount());

        holder.btnFavrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Tag", "onClick: "+position);

//                String newsTitle = articleList.get(position).getTitle();
//                String newsPubDate = holder.tvArticlePublishDate.getText().toString();
                NewsHeadlineObject newsHeadline= new NewsHeadlineObject(newsArticleTitle, newArticlePublishDate);
                long row = databaseHelper.saveNewsHeadline(newsHeadline);
                if (row>0){
                    holder.btnFavrt.setBackgroundColor(Color.parseColor("#FF0000"));
                    Log.d("TAG", "onClick: "+position);
                }else{
                    Toast.makeText(context, "Already Saved", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvArticleTile;
        private final TextView tvArticlePublishDate;
        private final Button btnFavrt;

        public ViewHolder(View itemView) {
            super(itemView);

            tvArticleTile = itemView.findViewById(R.id.news_article_title);
            tvArticlePublishDate = itemView.findViewById(R.id.news_article_date_publish);
            btnFavrt = itemView.findViewById(R.id.btnSavetoFavt);




        }
    }

}
