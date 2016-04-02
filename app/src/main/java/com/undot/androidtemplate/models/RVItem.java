package com.undot.androidtemplate.models;

/**
 * Created by 0503337710 on 02/04/2016.
 */
public class RVItem {
    private String name;
    private String url;

    public RVItem(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
