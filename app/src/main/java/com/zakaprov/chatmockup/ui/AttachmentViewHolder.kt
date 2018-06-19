package com.zakaprov.chatmockup.ui

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import com.bumptech.glide.RequestManager
import com.zakaprov.chatmockup.extensions.getParentMessage
import com.zakaprov.chatmockup.model.Attachment
import com.zakaprov.chatmockup.model.User
import kotlinx.android.synthetic.main.item_attachment.view.*

class AttachmentViewHolder(val view: View, val glideManager: RequestManager) : RecyclerView.ViewHolder(view) {

    fun bind(attachment: Attachment) {
        if (attachment.getParentMessage()?.userId == User.SESSION_USER_ID) {
            bindSentAttachment(attachment)
        } else {
            bindReceivedAttachment(attachment)
        }
    }

    private fun bindSentAttachment(attachment: Attachment) = with(view) {
        item_attachment_root.gravity = Gravity.END

        glideManager.clear(item_attachment_image)
        glideManager
            .load(attachment.url)
            .into(item_attachment_image)


        item_attachment_title.text = attachment.title
    }

    private fun bindReceivedAttachment(attachment: Attachment) = with(view) {
        item_attachment_root.gravity = Gravity.START

        glideManager.clear(item_attachment_image)
        glideManager
            .load(attachment.url)
            .into(item_attachment_image)


        item_attachment_title.text = attachment.title
    }
}
