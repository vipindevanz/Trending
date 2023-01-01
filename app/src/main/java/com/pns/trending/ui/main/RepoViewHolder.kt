package com.pns.trending.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.pns.trending.data.entities.Repo
import com.pns.trending.databinding.RepoItemLayoutBinding

class RepoViewHolder(
    private val repoItemLayoutBinding: RepoItemLayoutBinding,
    private val clickListener: ClickListener
) :
    RecyclerView.ViewHolder(repoItemLayoutBinding.root) {

    var repoItemDescHolder = repoItemLayoutBinding.repoItemDescHolder

    fun setData(list: List<Repo>, repo: Repo) {
        repoItemLayoutBinding.apply {

            this.repo = repo

            this.repoMainHolder.setOnClickListener {
                clickListener.onClick(repoItemDescHolder, list, adapterPosition)
            }
        }
    }
}