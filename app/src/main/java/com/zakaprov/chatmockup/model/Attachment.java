package com.zakaprov.chatmockup.model;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;

public class Attachment extends RealmObject implements ChatItem {

    @PrimaryKey private String id;
    private String title;
    private String url;
    private String thumbnailUrl;
    @LinkingObjects("attachments") private final RealmResults<Message> parentMessage = null;

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
