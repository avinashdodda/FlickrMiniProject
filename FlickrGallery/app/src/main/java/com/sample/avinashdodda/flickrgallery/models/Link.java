package com.sample.avinashdodda.flickrgallery.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by Avinash Dodda on 5/16/15.
 */

@Root(name = "link", strict = false)
public class Link
{
    @Attribute
    private String rel;

    @Attribute
    private String type;

    @Attribute
    private String href;

    public String getRel ()
    {
        return rel;
    }

    public String getType ()
    {
        return type;
    }

    public String getHref ()
    {
        return href;
    }
}
