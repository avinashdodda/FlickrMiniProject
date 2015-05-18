package com.sample.avinashdodda.flickrgallery.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.avinashdodda.flickrgallery.R;

/**
 * Created by Avinash Dodda on 5/16/15.
 */

public class FlickrGalleryFragment extends Fragment {

    public FlickrGalleryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }
}
