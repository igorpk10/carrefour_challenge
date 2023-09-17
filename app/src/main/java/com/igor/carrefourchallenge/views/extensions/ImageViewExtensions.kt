package com.igor.carrefourchallenge.views.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.fetchImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}