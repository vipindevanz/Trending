package com.pns.trending.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pns.trending.data.entities.Repo
import com.pns.trending.data.repositories.RepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RepoRepository) : ViewModel() {

    val reposLiveData : LiveData<List<Repo>>
    get() = repository.repos

    init {
        viewModelScope.launch {
            repository.getRepos()
        }
    }
}