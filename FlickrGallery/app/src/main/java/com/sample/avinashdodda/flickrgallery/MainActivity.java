package com.sample.avinashdodda.flickrgallery;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.sample.avinashdodda.flickrgallery.adapters.ImageAdapter;
import com.sample.avinashdodda.flickrgallery.models.Entry;
import com.sample.avinashdodda.flickrgallery.models.Feed;
import com.sample.avinashdodda.flickrgallery.request.FlickrApiRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avinash Dodda on 5/16/15.
 */

public class MainActivity extends Activity {

    private static final String KEY_LAST_REQUEST_CACHE_KEY = "lastRequestCacheKey";
    private static final String CITY_NAME_PARAM = "boston";

    private SpiceManager spiceManager = new SpiceManager(
            JacksonSpringAndroidSpiceService.class);

    private String lastRequestCacheKey;
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Entry> entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        performRequest(CITY_NAME_PARAM);
        initUIComponents();
    }

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        FlickrApiRequest request = new FlickrApiRequest(CITY_NAME_PARAM);
        spiceManager.execute(request, lastRequestCacheKey, DurationInMillis.ONE_MINUTE * 10, new NewDataRequestListener());
    }

    private void initUIComponents() {
        mRecyclerView = (RecyclerView) findViewById(R.id.flickr_grid_view);
        mRecyclerView.setHasFixedSize(true);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            //change layout for orientation changes
            mLayoutManager = new GridLayoutManager(this, 2);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            //change layout for orientation changes
            mLayoutManager = new GridLayoutManager(this, 4);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
        mAdapter = new ImageAdapter(this, new ArrayList<Entry>());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void performRequest(String cityName) {
        FlickrApiRequest request = new FlickrApiRequest(cityName);
        lastRequestCacheKey = request.createCacheKey();
        FeedRequestListener requestListener = new FeedRequestListener();
        spiceManager.getFromCacheAndLoadFromNetworkIfExpired(request, lastRequestCacheKey, DurationInMillis.ALWAYS_RETURNED, requestListener);
    }

    @Override
    protected void onDestroy() {
        spiceManager.removeDataFromCache(Feed.class);
        super.onDestroy();
    }

    private class FeedRequestListener implements
            RequestListener<Feed> {

    @Override
    public void onRequestFailure(SpiceException e) {

    }

    @Override
    public void onRequestSuccess(Feed feed) {
        if (feed == null) return;
        if(feed.getEntry() == null) return;
        mAdapter.clear();
        entries = feed.getEntry();
        mAdapter.fill(entries);
        }
    }

    private class NewDataRequestListener implements
            RequestListener<Feed> {

        @Override
        public void onRequestFailure(SpiceException e) {

        }

        @Override
        public void onRequestSuccess(Feed feed) {
            spiceManager.removeAllDataFromCache();
            spiceManager.putInCache(Feed.class, lastRequestCacheKey, feed);
        }
    }
}
