package android.example.guardiannews.adapter;

import android.content.Context;
import android.example.guardiannews.R;
import android.example.guardiannews.data.News;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<News> newsArrayList;

    private Context context;

    // private  ListItemClickListener mOnListItemClickListener;

  public   NewsAdapter(ArrayList<News> newsArrayList) {
        this.newsArrayList = newsArrayList;
    }

    public void clear() {
        newsArrayList = new ArrayList<>();
    }


    public void addAll(List<News> data) {
        // Traverse the data list to add books to the adapter's data set
        for (int i = 0; i < data.size(); i++) {
            // Get the book at current index
            News news = data.get(i);
            // Add the book to the data set
            newsArrayList.add(news);

            // Notify the adapter of the change in the data set
            // so that it repopulates the view with the updated data set
            notifyDataSetChanged();
        }
    }

//
//    public void setOnListItemClickListener(ListItemClickListener listItemClickListener)
//    {
//         mOnListItemClickListener=listItemClickListener;
//    }
//
//    public interface ListItemClickListener{
//        void onListItemClick(int position);
//
//    }

//
//    public void setNews(ArrayList<News>list)
//    {
//        newsArrayList=list;
//    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_items, parent, false);


        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        News currentNews = newsArrayList.get(position);

        holder.newsSectionNameView.setText(currentNews.getNewsSectionName());

        holder.newsTitleView.setText(currentNews.getNewsTitle());

        holder.newsPublishedDateView.setText(currentNews.getNewsPublishedDate());

        if (currentNews.getNewsThumbnail() != null) {
            Picasso.get()
                    .load(currentNews.getNewsThumbnail())
                    .fit()
                    .into(holder.newsThumbnailView);
        } else {
            Picasso.get()
                    .load(R.drawable.samplenewslogo)
                    .into(holder.newsThumbnailView);
        }


    }

    @Override
    public int getItemCount() {

        return newsArrayList.size();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder {


        private TextView newsSectionNameView;
        private TextView newsTitleView;
        private ImageView newsThumbnailView;
        private TextView newsPublishedDateView;

       NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            newsSectionNameView = itemView.findViewById(R.id.sectionName);
            newsTitleView = itemView.findViewById(R.id.newsTitle);
            newsThumbnailView = itemView.findViewById(R.id.newsThumbnail);
            newsPublishedDateView = itemView.findViewById(R.id.pdt);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mOnListItemClickListener != null) {
//                        mOnListItemClickListener.onListItemClick(getAdapterPosition());
//                    }
//                }
//            });

        }

//        @Override
//        public void onClick(View v) {
//         int position=getAdapterPosition();
//
//         News news=newsArrayList.get(position);
//
//            Uri uri=Uri.parse(news.getNewsUrl());
//
//
//            CustomTabsIntent.Builder builder= new CustomTabsIntent.Builder();
//            CustomTabsIntent customTabsIntent=builder.build();
//
//            /*
//            /check this below statement and modify  if you get any error
//             */
//
//            customTabsIntent.launchUrl(v.getContext(), uri);
//        }
    }


}
