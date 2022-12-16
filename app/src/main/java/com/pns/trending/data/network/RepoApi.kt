package com.pns.trending.data.network

import com.pns.trending.data.entities.Repo
import retrofit2.Response
import retrofit2.http.GET

interface RepoApi {

    @GET("testing")
    suspend fun getRepos() : Response<List<Repo>>
}