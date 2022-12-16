package com.pns.trending.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pns.trending.R
import com.pns.trending.data.entities.Repo
import com.pns.trending.utilities.Utilities.EXPANDED_ITEM_POS

class RepoAdapter(val list: ArrayList<Repo>, private val clickListener: ClickListener) :
    RecyclerView.Adapter<RepoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.repo_item_layout,
                parent,
                false
            ), clickListener
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val data = list[position]
        holder.setData(list, data)

        if (EXPANDED_ITEM_POS >=0 && position != EXPANDED_ITEM_POS){
            holder.repoItemDescHolder.visibility = View.GONE
            data.expanded = false
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}