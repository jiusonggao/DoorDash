package com.jiusong.doordash

import com.jiusong.doordash.data.network.DoorDashAPi
import com.jiusong.doordash.data.network.DoorDashServiceFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

/**
 * Created by jiusong.gao on 1/15/21.
 */
class DoorDashApiTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: DoorDashAPi

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        apiService = DoorDashServiceFactory.retrofit(mockWebServer.url("/").toString()).create(DoorDashAPi::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testFetchStoresSuccess() {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(FileReader("stores_feed.json").content)
        mockWebServer.enqueue(response)
        // Act
        val storeFeed = runBlocking { apiService.getStores(HashMap<String, String>()) }
        // Assert
        assert(storeFeed.next_offset == 5)
        assert(storeFeed.stores.size == 5)
    }

    @Test
    fun testFetchStoresFail() {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(FileReader("store_feed_no_offset_limit.json").content)
        mockWebServer.enqueue(response)
        // Act
        val storeFeed = runBlocking { apiService.getStores(HashMap<String, String>()) }
        // Assert
        assert(storeFeed.limit[0] == "This field is required.")
        assert(storeFeed.offset[0] == "This field is required.")
    }
}