package com.zakaprov.chatmockup.extensions

import com.zakaprov.chatmockup.model.Attachment
import com.zakaprov.chatmockup.model.Message
import com.zakaprov.chatmockup.model.User
import io.realm.Realm

fun User.queryById(id: Long) = Realm.getDefaultInstance().use {
    it.where(javaClass)
        .equalTo("id", id)
        .findFirst()
}

fun Attachment.getParentMessage() = Realm.getDefaultInstance().use {
    it.where(Message::class.java)
        .equalTo("attachments.id", id)
        .findFirst()
}
