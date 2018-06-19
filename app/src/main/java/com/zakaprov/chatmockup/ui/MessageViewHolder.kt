package com.zakaprov.chatmockup.ui

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.zakaprov.chatmockup.R
import com.zakaprov.chatmockup.extensions.invisible
import com.zakaprov.chatmockup.extensions.queryById
import com.zakaprov.chatmockup.extensions.visible
import com.zakaprov.chatmockup.model.Message
import com.zakaprov.chatmockup.model.User
import kotlinx.android.synthetic.main.item_message.view.*

class MessageViewHolder(val view: View, val glideManager: RequestManager) : RecyclerView.ViewHolder(view) {

    fun bind(message: Message) {
        if (message.userId == User.SESSION_USER_ID) {
            bindSentMessage(message)
        } else {
            bindReceivedMessage(message)
        }
    }

    private fun bindSentMessage(message: Message) = with(view) {
        item_msg_root.gravity = Gravity.END

        item_msg_content_root.background = view.resources.getDrawable(R.drawable.background_msg_gray, null)
        item_msg_avatar.invisible()
        item_msg_username.invisible()
        item_msg_label_me.visible()

        item_msg_content.text = message.content
    }

    private fun bindReceivedMessage(message: Message) = with(view) {
        item_msg_root.gravity = Gravity.START

        item_msg_content_root.background = view.resources.getDrawable(R.drawable.background_msg_white, null)
        item_msg_avatar.visible()
        item_msg_username.visible()
        item_msg_label_me.invisible()

        glideManager.clear(item_msg_avatar)
        glideManager
            .load(User().queryById(message.userId)?.avatarId)
            .apply(RequestOptions.circleCropTransform())
            .into(item_msg_avatar)

        item_msg_content.text = message.content
        item_msg_username.text = User().queryById(message.userId)?.name
    }
}
