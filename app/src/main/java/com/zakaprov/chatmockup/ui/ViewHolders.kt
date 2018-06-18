package com.zakaprov.chatmockup.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import com.zakaprov.chatmockup.extensions.queryById
import com.zakaprov.chatmockup.model.Attachment
import com.zakaprov.chatmockup.model.Message
import com.zakaprov.chatmockup.model.User
import kotlinx.android.synthetic.main.item_attachment.view.*
import kotlinx.android.synthetic.main.item_message.view.*

class MessageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(message: Message) {
        view.message_text_user_name.text = User().queryById(message.userId)?.name
        view.message_text_content.text = message.content
    }
}

class AttachmentViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(attachment: Attachment) {
        view.attachment_text_file_name.text = attachment.title
    }
}
