package com.pns.trending.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pns.trending.Converters
import com.pns.trending.data.dao.RepoDao
import com.pns.trending.data.entities.Repo

@Database(entities = [Repo::class], version = 1)
@TypeConverters(Converters::class)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun getRepoDao() : RepoDao

}