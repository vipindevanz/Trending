package com.pns.trending.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pns.trending.data.RepoDatabase
import com.pns.trending.data.entities.Repo
import com.pns.trending.data.network.RepoApi
import javax.inject.Inject

class RepoRepository @Inject constructor(private val repoApi: RepoApi, private val repoDatabase: RepoDatabase) {

    private val _repos = MutableLiveData<List<Repo>>()
    private val _status = MutableLiveData<Boolean>()

    val repos: LiveData<List<Repo>>
    get() = _repos

    val status: LiveData<Boolean>
        get() = _status

    suspend fun getRepos(){

        try {
            val result = repoApi.getRepos()
            if (result.isSuccessful && result.body() != null) {
                repoDatabase.getRepoDao().addRepos(result.body()!!)
                _repos.postValue(result.body())
                _status.postValue(true)
            } else{
                _status.postValue(false)
            }
        } catch (e : Exception){
            _status.postValue(false)
            Log.d("TAG", e.toString())
            _repos.postValue(repoDatabase.getRepoDao().getrepos())
        }
    }
}