package com.sample.avinashdodda.flickrgallery.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;


/**
 * Created by Avinash Dodda on 5/16/15.
 */

@Root(name = "feed", strict = false)
public class Feed
{
    @Element
    private String title;

    @Element
    private String updated;

    @ElementList(inline = true)
    private List<Entry> entry;

    public String getTitle ()
    {
        return title;
    }

    public List<Entry> getEntry ()
    {
        return entry;
    }

    public String getUpdated() {
        return updated;
    }
}
