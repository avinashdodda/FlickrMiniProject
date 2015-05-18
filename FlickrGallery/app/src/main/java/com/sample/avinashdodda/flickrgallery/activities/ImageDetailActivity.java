package com.sample.avinashdodda.flickrgallery.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.avinashdodda.flickrgallery.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Avinash Dodda on 5/16/15.
 */

public class ImageDetailActivity extends Activity {

    private ImageView imageView;
    private TextView authorNameTV;
    private ImageView authorImageView;
    private static final String INTENT_IMAGE_EXTRA = "imageData";
    private static final String INTENT_AUTHOR_NAME = "authorName";
    private static final String INTENT_AUTHOR_IMAGE = "authorImage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        initUIComponents();
    }

    // initializing the ui from the intent
    private void initUIComponents() {
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(INTENT_IMAGE_EXTRA);
        String authorName = intent.getStringExtra(INTENT_AUTHOR_NAME);
        String authorImage = intent.getStringExtra(INTENT_AUTHOR_IMAGE);

        imageView = (ImageView)findViewById(R.id.detail_image_view);
        authorNameTV = (TextView)findViewById(R.id.author_name_text_view);
        authorImageView = (ImageView)findViewById(R.id.author_image_view);

        Picasso.with(this)
                .load(imageUrl)
                .fit()
                .centerCrop()
                .into(imageView);
        if(authorName != null && authorImage!= null) {
            authorNameTV.setText(authorName);
            Picasso.with(this)
                    .load(authorImage)
                    .fit()
                    .centerCrop()
                    .into(authorImageView);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_detail, menu);
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
}
