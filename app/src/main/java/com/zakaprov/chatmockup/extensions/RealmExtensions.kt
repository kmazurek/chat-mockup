package com.zakaprov.chatmockup.extensions

import com.zakaprov.chatmockup.model.Attachment
import com.zakaprov.chatmockup.model.Message
import com.zakaprov.chatmockup.model.User
import io.realm.Realm
import io.realm.RealmModel
import io.realm.kotlin.deleteFromRealm

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

fun <T : RealmModel> Realm.queryById(id: Long, objectClass: Class<T>) = this.where(objectClass)
    .equalTo("id", id)
    .findFirst()

fun <T : RealmModel> Realm.queryById(id: String, objectClass: Class<T>) = this.where(objectClass)
    .equalTo("id", id)
    .findFirst()

fun Realm.deleteFromRealm(item: RealmModel?) {
    item ?: return
    this.executeTransaction {
        item.deleteFromRealm()
    }
}
