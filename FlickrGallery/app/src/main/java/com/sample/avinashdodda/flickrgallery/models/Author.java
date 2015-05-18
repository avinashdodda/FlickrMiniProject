package com.sample.avinashdodda.flickrgallery.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by Avinash Dodda on 5/16/15.
 */

@Root(name = "author", strict = false)
public class Author {

    @Element
    private String name;

    @Namespace(prefix="flickr")
    @Element
    private String buddyicon;

    public String getName ()
    {
        return name;
    }

    public String getBuddyicon() {
        return buddyicon;
    }
}
