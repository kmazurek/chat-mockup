package com.zakaprov.chatmockup.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Attachment extends RealmObject {

    @PrimaryKey private String id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
