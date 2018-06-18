package com.zakaprov.chatmockup.extensions

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun @receiver:LayoutRes Int.inflate(parent: ViewGroup): View = LayoutInflater.from(parent.context).inflate(this, parent, false)