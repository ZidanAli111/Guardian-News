package android.example.guardiannews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.example.guardiannews.Utils.NewsUtils;
import android.example.guardiannews.adapter.NewsAdapter;
import android.example.guardiannews.data.News;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private ProgressBar loadingIndicatorBar;
    private TextView emptyTextView;
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;
    private  String mUrl;
    private  ArrayList<News>mArrayList;
    private static final int LOADER_ID = 1;

    private static final String JSON_RESPONSE = "https://content.guardianapis.com/search?page-size=200&show-fields=thumbnail&api-key=7e7ebf19-ad71-4b2a-b8c4-f434b435a3db";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycler_view_id);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);



        loadingIndicatorBar=findViewById(R.id.loading_indicator);

        newsAdapter=new NewsAdapter(new ArrayList<News>());

        recyclerView.setAdapter(newsAdapter);
        LoaderManager loaderManager=getLoaderManager();

       loaderManager.initLoader(LOADER_ID,null,MainActivity.this);


    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {

        return new NewsLoader(MainActivity.this,JSON_RESPONSE);

        }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {

        loadingIndicatorBar.setVisibility(View.GONE);

        newsAdapter.clear();

        if (data!=null&&!data.isEmpty()) {
            newsAdapter.addAll(data);

        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        newsAdapter.clear();

    }
}