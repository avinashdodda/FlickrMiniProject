package com.sample.avinashdodda.flickrgallery.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sample.avinashdodda.flickrgallery.R;
import com.sample.avinashdodda.flickrgallery.models.Entry;
import com.sample.avinashdodda.flickrgallery.models.Link;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Avinash Dodda on 5/16/15.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private static final String LINK_TYPE = "image/jpeg";

    private List<Entry> mDataset;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
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

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Entry entry = mDataset.get(position);
        for(Link link : entry.getLink()) {
            if(link.getType().equals(LINK_TYPE)) {
                Picasso.with(context)
                        .load(link.getHref())
                        .fit()
                        .centerCrop()
                        .into(holder.mImageView);
            }
        }
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

    public void fill(List<Entry> entries) {
        for(Entry entry : entries) {
            mDataset.add(entry);
        }
        notifyDataSetChanged();
    }
}