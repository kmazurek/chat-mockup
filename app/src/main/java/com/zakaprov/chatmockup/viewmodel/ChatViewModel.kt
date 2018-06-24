package com.zakaprov.chatmockup.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.zakaprov.chatmockup.extensions.deleteFromRealm
import com.zakaprov.chatmockup.extensions.queryById
import com.zakaprov.chatmockup.model.Attachment
import com.zakaprov.chatmockup.model.Message
import io.realm.Realm
import io.realm.RealmResults

private const val PAGE_SIZE = 20

class ChatViewModel : ViewModel() {

    private val realm: Realm by lazy { Realm.getDefaultInstance() }
    private val results: RealmResults<Message> by lazy {
        realm.where(Message::class.java)
            .sort("id")
            .findAll()
    }

    private val messages: MutableLiveData<List<Message>> = MutableLiveData()
    private var pagesLoaded = 0

    fun deleteAttachment(id: String) = realm.apply { deleteFromRealm(queryById(id, Attachment::class.java)) }

    fun deleteMessage(id: Long) = realm.apply { deleteFromRealm(queryById(id, Message::class.java)) }

    fun getMessages(): LiveData<List<Message>> {
        if (pagesLoaded == 0) {
            loadNextPage()

            results.addChangeListener({ updatedResults ->
                messages.postValue(realm.copyFromRealm(updatedResults.take(pagesLoaded * PAGE_SIZE)))
            })
        }

        return messages
    }

    fun loadNextPage() = messages.postValue(realm.copyFromRealm(results.take(++pagesLoaded * PAGE_SIZE)))
}
