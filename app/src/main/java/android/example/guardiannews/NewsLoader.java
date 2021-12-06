package android.example.guardiannews;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.example.guardiannews.Utils.NewsUtils;
import android.example.guardiannews.data.News;
import android.view.View;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {



    private String mSearchUrl;

    /** Data from the API */
    private List<News> mData;



    public NewsLoader(Context context,String url) {
        super(context);
        mSearchUrl=url;

    }


    @Override
    protected void onStartLoading() {
        if (mData != null) {
            deliverResult(mData); // Use cached data
        } else {
            forceLoad();
        }
    }

    @Override
    public List<News> loadInBackground() {

        if (mSearchUrl == null) {
            return null;
        }
        List<News> newsList = NewsUtils.fetchNews(mSearchUrl);

        return newsList;
    }

    @Override

    public void deliverResult(List<News> data) {
        mData = data;
        super.deliverResult(data);
    }

}
