package com.pns.trending.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pns.trending.data.entities.Repo
import com.pns.trending.databinding.RepoItemLayoutBinding

class RepoViewHolder(
    private val repoItemLayoutBinding: RepoItemLayoutBinding,
    private val clickListener: ClickListener
) :
    RecyclerView.ViewHolder(repoItemLayoutBinding.root) {

    var repoItemDescHolder = repoItemLayoutBinding.repoItemDescHolder

    fun setData(list : List<Repo> ,repo: Repo) {
        repoItemLayoutBinding.apply {

            Glide.with(repoItemDescHolder).load(repo.avatar).into(repoItemAvatar)

            repoItemAuthor.text = repo.author
            repoItemName.text = repo.name
            repoItemDesc.text = repo.description

            repoItemLanguage.text = repo.language
            repoItemStars.text = repo.stars.toString()
            repoItemForks.text = repo.forks.toString()

            this.repoMainHolder.setOnClickListener {
                clickListener.onClick(repoItemDescHolder, list, adapterPosition)
            }
        }
    }
}