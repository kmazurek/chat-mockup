package com.zakaprov.chatmockup.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.zakaprov.chatmockup.R
import com.zakaprov.chatmockup.viewmodel.ChatViewModel
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    private lateinit var chatAdapter: ChatListAdapter
    private lateinit var chatLayoutManager: LinearLayoutManager
    private lateinit var viewModel: ChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        viewModel = ViewModelProviders.of(this).get(ChatViewModel::class.java)

        chatAdapter = ChatListAdapter(Glide.with(this), {
            viewModel.deleteMessage(it)
        }, {
            viewModel.deleteAttachment(it)
        })

        chatLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)

        chat_recycler_view.apply {
            adapter = chatAdapter
            layoutManager = chatLayoutManager
        }

        viewModel.getMessages().observe(this, Observer {
            it ?: return@Observer
            chatAdapter.updateMessages(it)
        })
    }
}
