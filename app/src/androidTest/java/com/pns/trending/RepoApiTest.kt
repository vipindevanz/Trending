package com.pns.trending

import com.pns.trending.data.network.RepoApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoApiTest {

    lateinit var mockWebServer : MockWebServer
    lateinit var repoApi: RepoApi

    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        repoApi = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(repoApi::class.java)
    }

    @Test
    fun testGetRepos() = runTest {

        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = repoApi.getRepos()
        mockWebServer.takeRequest()

        Assert.assertEquals(true, response.body()!!.isEmpty())

    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}