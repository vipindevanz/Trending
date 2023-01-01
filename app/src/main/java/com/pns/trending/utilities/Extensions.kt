package com.pns.trending.utilities

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.pns.trending.data.entities.Repo
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("image")
fun CircleImageView.setImage(url:String) {

    Glide.with(this.context)
        .load(url)
        .into(this)
}