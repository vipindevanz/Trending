package com.pns.trending.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pns.trending.data.RepoDatabase
import com.pns.trending.data.entities.Repo
import com.pns.trending.data.network.RepoApi
import javax.inject.Inject

class RepoRepository @Inject constructor(private val repoApi: RepoApi, private val repoDatabase: RepoDatabase) {

    private val _repos = MutableLiveData<List<Repo>>()
    val repos: LiveData<List<Repo>>
    get() = _repos

    suspend fun getRepos(){
        val result = repoApi.getRepos()
        if(result.isSuccessful && result.body() != null){
            repoDatabase.getRepoDao().addRepos(result.body()!!)
            _repos.postValue(result.body())
        }
    }
}