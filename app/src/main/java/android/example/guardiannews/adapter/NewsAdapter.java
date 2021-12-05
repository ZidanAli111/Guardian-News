package android.example.guardiannews.adapter;

import android.example.guardiannews.MainActivity;
import android.example.guardiannews.R;
import android.example.guardiannews.data.News;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<News> newsArrayList;

    public NewsAdapter(ArrayList<News> newsArrayList) {
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_items, parent, false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(view);

        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.newsSectionNameView.setText(newsArrayList.get(position).getNewsSectionName());
        holder.newsTitleView.setText(newsArrayList.get(position).getNewsTitle());
        holder.newsPublishedDateView.setText(newsArrayList.get(position).getNewsPublishedDate());
        if (newsArrayList.get(position).getNewsThumbnail() != null) {
            Picasso.get()
                    .load(newsArrayList.get(position).getNewsThumbnail())
                    .fit()
                    .into(holder.newsThumbnailView);
        } else {
            Picasso.get()
                    .load(R.drawable.newsLogo)
                    .centerInside()
                    .into(holder.newsThumbnailView);
        }


    }

    @Override
    public int getItemCount() {
        
        return newsArrayList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView newsSectionNameView;
        private TextView newsTitleView;
        private ImageView newsThumbnailView;
        private TextView newsPublishedDateView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            newsSectionNameView = itemView.findViewById(R.id.sectionName);
            newsTitleView = itemView.findViewById(R.id.newsTitle);
            newsThumbnailView = itemView.findViewById(R.id.newsThumbnail);
            newsPublishedDateView = itemView.findViewById(R.id.pdt);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
         int position=getAdapterPosition();

         News news=newsArrayList.get(position);

            Uri uri=Uri.parse(news.getNewsUrl());


            CustomTabsIntent.Builder builder= new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent=builder.build();

            /*
            /check this below statement and modify  if you get any error
             */

            customTabsIntent.launchUrl(v.getContext(), uri);
        }
    }
}
