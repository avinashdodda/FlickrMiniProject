package com.sample.avinashdodda.flickrgallery.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Avinash Dodda on 5/16/15.
 */

@Root(name = "entry", strict = false)
public class Entry
{
    @Element
    private Author author;

    @Element
    private String title;

    @ElementList(inline = true)
    private List<Link> link;

    public Author getAuthor ()
    {
        return author;
    }

    public String getTitle ()
    {
        return title;
    }

    public List<Link> getLink ()
    {
        return link;
    }
}
