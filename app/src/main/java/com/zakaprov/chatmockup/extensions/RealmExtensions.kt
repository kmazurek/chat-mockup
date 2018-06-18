package com.zakaprov.chatmockup.extensions

import com.zakaprov.chatmockup.model.User
import io.realm.Realm

fun User.queryById(id: Long) = Realm.getDefaultInstance().use {
    it.where(javaClass).equalTo(User.FIELD_ID, id).findFirst()
}
