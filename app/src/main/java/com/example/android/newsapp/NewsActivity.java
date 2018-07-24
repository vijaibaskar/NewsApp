package com.example.android.newsapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderCallbacks<List<News>>{

    private NewsAdapter mAdapter;
    private TextView mEmptyStateTextView;
    private static final int NEWS_LOADER_ID = 1;

    private static final String GUAR_REQUEST_URL = "https://content.guardianapis.com/search?show-tags=contributor&api-key=fbfed1fc-1c50-40b2-8392-d4a19218006c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        ListView newsListView = findViewById(R.id.list);

        mAdapter = new NewsAdapter(this, new ArrayList<News>());

        newsListView.setAdapter(mAdapter);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);

        if (!isConnected) {
            View loadingIndicator = findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = mAdapter.getItem(position);
                Uri newsUri = Uri.parse(currentNews.getUrl());
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(browserIntent);
            }
        });

        LoaderManager loaderManager = getLoaderManager();

        loaderManager.initLoader(NEWS_LOADER_ID, null, this);
    }

        @Override
        public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
            return new NewsLoader(this, GUAR_REQUEST_URL);
        }

        @Override
        public void onLoadFinished(Loader<List<News>> loader, List<News> newsStories) {

            View loadingIndicator = findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_data_available);

            mAdapter.clear();


            if (newsStories != null && !newsStories.isEmpty()){
                mAdapter.addAll(newsStories);
            }


        }

        @Override
        public void onLoaderReset(Loader<List<News>> loader) {
            // TODO: Loader reset, so we can clear out our existing data.
            mAdapter.clear();

        }
    }

