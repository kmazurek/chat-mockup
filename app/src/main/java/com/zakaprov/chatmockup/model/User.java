package com.zakaprov.chatmockup.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    @PrimaryKey private Long id;
    private String name;
    private String avatarId;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatarId() {
        return avatarId;
    }
}
