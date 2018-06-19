package com.zakaprov.chatmockup.ui

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.zakaprov.chatmockup.R
import com.zakaprov.chatmockup.extensions.inflate
import com.zakaprov.chatmockup.model.Attachment
import com.zakaprov.chatmockup.model.ChatItem
import com.zakaprov.chatmockup.model.Message

private const val TYPE_ATTACHMENT = 100
private const val TYPE_MESSAGE = 101

class ChatListAdapter(val glideManager: RequestManager)
    : ListAdapter<ChatItem, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    private var items: MutableList<ChatItem> = mutableListOf()

    fun addMessages(messages: Iterable<Message>) {
        items.addAll(mapMessages(messages))
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = with(items[position]) {
        when(this) {
            is Message -> TYPE_MESSAGE
            is Attachment -> TYPE_ATTACHMENT
            else -> throw IllegalArgumentException()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when(viewType) {
        TYPE_MESSAGE -> MessageViewHolder(R.layout.item_message.inflate(parent), glideManager)
        TYPE_ATTACHMENT -> AttachmentViewHolder(R.layout.item_attachment.inflate(parent), glideManager)
        else -> throw IllegalArgumentException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = with(items[position]) {
        when(holder) {
            is MessageViewHolder -> holder.bind(this as Message)
            is AttachmentViewHolder -> holder.bind(this as Attachment)
            else -> throw IllegalArgumentException()
        }
    }

    private fun mapMessages(messages: Iterable<Message>) = messages.flatMap {
        val result = mutableListOf<ChatItem>()

        messages.forEach {
            result.addAll(it.attachments.reversed())
            result.add(it)
        }

        result
    }
}

private val DIFF_CALLBACK: DiffUtil.ItemCallback<ChatItem> = object : DiffUtil.ItemCallback<ChatItem>() {

    override fun areItemsTheSame(oldItem: ChatItem, newItem: ChatItem) = when {
        oldItem is Message && newItem is Message -> oldItem.id == newItem.id
        oldItem is Attachment && newItem is Attachment -> oldItem.id == newItem.id
        else -> false
    }

    override fun areContentsTheSame(oldItem: ChatItem, newItem: ChatItem) = when {
        oldItem is Message && newItem is Message -> oldItem.content == newItem.content
        oldItem is Attachment && newItem is Attachment -> oldItem.url == newItem.url
        else -> false
    }
}
