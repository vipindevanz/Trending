package com.pns.trending.di

import android.content.Context
import androidx.room.Room
import com.pns.trending.data.RepoDatabase
import com.pns.trending.utilities.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRepoDatabase(@ApplicationContext context : Context) : RepoDatabase {
        return Room.databaseBuilder(context, RepoDatabase::class.java, DB_NAME).build()
    }
}