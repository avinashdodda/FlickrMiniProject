package com.sample.avinashdodda.flickrgallery.requests;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import com.sample.avinashdodda.flickrgallery.models.Feed;

import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;

/**
 * Created by Avinash Dodda on 5/16/15.
 */

public class FlickrApiRequest extends SpringAndroidSpiceRequest<Feed> {

    private static final String FLICKR_URL = "https://api.flickr.com/services/feeds/photos_public.gne";
    private String cityName;
    private String cacheKey;

    public FlickrApiRequest(String cityName) {
        super(Feed.class);
        this.cityName = cityName;
    }

    @Override
    public Feed loadDataFromNetwork() throws Exception {

        String url = String.format(FLICKR_URL +"?tags=%s", cityName);

        // Add the Simple XML message converter
        getRestTemplate().getMessageConverters().add(new SimpleXmlHttpMessageConverter());
        return getRestTemplate().getForObject(url, Feed.class);
    }

    /**
     * This method generates a unique cache key for this request. In this case
     * our cache key depends just on the keyword.
     *
     * @return
     */
    public String createCacheKey() {
        cacheKey = "feed." + cityName;
        return cacheKey;
    }

    public String getCache() {
        return cacheKey;
    }
}
