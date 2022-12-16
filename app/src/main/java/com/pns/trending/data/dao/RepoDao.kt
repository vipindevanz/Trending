package com.pns.trending.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pns.trending.data.entities.Repo

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRepos(repos : List<Repo>)

    @Query("SELECT * FROM Repo")
    suspend fun getrepos() : List<Repo>

}