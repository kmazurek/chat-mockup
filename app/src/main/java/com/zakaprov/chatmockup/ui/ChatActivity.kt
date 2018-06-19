package com.zakaprov.chatmockup.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.zakaprov.chatmockup.R
import com.zakaprov.chatmockup.R.id.chat_recycler_view
import com.zakaprov.chatmockup.model.Message
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    private lateinit var chatAdapter: ChatListAdapter
    private lateinit var chatLayoutManager: LinearLayoutManager
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        realm = Realm.getDefaultInstance()
        chatAdapter = ChatListAdapter(Glide.with(this))

        chatLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)

        chat_recycler_view.apply {
            adapter = chatAdapter
            layoutManager = chatLayoutManager
        }

        chatAdapter.addMessages(realm.where(Message::class.java).findAll())
    }
}
