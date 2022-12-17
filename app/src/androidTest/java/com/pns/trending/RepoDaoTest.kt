package com.pns.trending

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.pns.trending.data.RepoDatabase
import com.pns.trending.data.dao.RepoDao
import com.pns.trending.data.entities.BuiltBy
import com.pns.trending.data.entities.Repo
import kotlinx.coroutines.runBlocking
import org.junit.*

class RepoDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var repoDatabase: RepoDatabase
    lateinit var repoDao: RepoDao

    @Before
    fun setup(){

        repoDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            repoDatabase::class.java
        ).allowMainThreadQueries().build()
        repoDao = repoDatabase.getRepoDao()
    }

    @Test
    fun insertRepo_expectedSingleRepo() = runBlocking {


        val builtBy : List<BuiltBy> = emptyList()
        val repo = Repo(author = "vipindev", "", "","","","","",0,0,0,builtBy)
        val repoList : List<Repo> = listOf(repo)
        repoDao.addRepos(repoList)

        val result = repoDao.getRepos().getOrAwaitValue()

        Assert.assertEquals(1, result.size)
    }

    @After
    fun tearDown(){
        repoDatabase.close()
    }
}