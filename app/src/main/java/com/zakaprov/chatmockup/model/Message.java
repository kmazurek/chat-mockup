package com.zakaprov.chatmockup.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Message extends RealmObject implements ChatItem {

    public static final String FIELD_ID = "id";

    @PrimaryKey private Long id;
    private Long userId;
    private String content;
    private RealmList<Attachment> attachments;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public RealmList<Attachment> getAttachments() {
        return attachments;
    }
}
