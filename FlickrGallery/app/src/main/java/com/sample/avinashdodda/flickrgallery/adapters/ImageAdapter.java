package com.sample.avinashdodda.flickrgallery.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sample.avinashdodda.flickrgallery.R;
import com.sample.avinashdodda.flickrgallery.activities.ImageDetailActivity;
import com.sample.avinashdodda.flickrgallery.models.Author;
import com.sample.avinashdodda.flickrgallery.models.Entry;
import com.sample.avinashdodda.flickrgallery.models.Link;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Avinash Dodda on 5/16/15.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> implements View.OnClickListener{

    private static final String LINK_TYPE = "image/jpeg";
    private static final String INTENT_IMAGE_EXTRA = "imageData";
    private static final String INTENT_AUTHOR_NAME = "authorName";
    private static final String INETENT_AUTHOR_IMAGE = "authorImage";

    private List<Entry> mDataset;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            mImageView = (ImageView)v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ImageAdapter(Context context, List<Entry> values) {
        this.context = context;
        this.mDataset = values;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_list_row_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public String getImageUrl(Entry entry) {
        String imageUrl = null;
        for(Link link : entry.getLink()) {
            if (link.getType().equals(LINK_TYPE)) {
                imageUrl = link.getHref();
            }
        }
        return imageUrl;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Entry entry = mDataset.get(position);
        ImageView imageHolder = holder.mImageView;
        String imageUrl = getImageUrl(entry);

        if( imageUrl != null) {
            Picasso.with(context)
                    .load(imageUrl)
                    .fit()
                    .centerCrop()
                    .into(imageHolder);
        }

        imageHolder.setOnClickListener(this);
        imageHolder.setTag(entry);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void clear() {
        mDataset.clear();
        notifyDataSetChanged();
    }

    // populating the dataset whenever data is returned from the api
    public void fill(List<Entry> entries) {
        for(Entry entry : entries) {
            mDataset.add(entry);
        }
        notifyDataSetChanged();
    }

    // navigating to another activity to show full screen view of the image selected
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.list_image_view:
                Entry entry = (Entry) view.getTag();
                Author author = entry.getAuthor();
                String imageUrl = getImageUrl(entry);
                if( imageUrl != null) {
                    Intent intent = new Intent(context, ImageDetailActivity.class);
                    intent.putExtra(INTENT_IMAGE_EXTRA, imageUrl);
                    if(author != null) {
                        intent.putExtra(INTENT_AUTHOR_NAME, author.getName());
                        intent.putExtra(INETENT_AUTHOR_IMAGE, author.getBuddyicon());
                    }
                    context.startActivity(intent);
                }
        }
    }
}