package com.pns.trending.ui.main

import android.widget.LinearLayout
import com.pns.trending.data.entities.Repo

interface ClickListener {
    fun onClick(holder: LinearLayout, list : List<Repo>, position: Int)
}