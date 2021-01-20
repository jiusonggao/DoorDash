package com.jiusong.doordash.viewmodel

import com.google.gson.Gson
import com.jiusong.doordash.FileReader
import com.jiusong.doordash.data.Repository
import com.jiusong.doordash.data.model.StoreFeedResponse
import com.jiusong.doordash.data.network.DoorDashAPi
import com.jiusong.doordash.data.network.DoorDashServiceFactory
import com.jiusong.doordash.util.DoorDashConstants
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by jiusong.gao on 1/18/21.
 */
class RestaurantDetailViewModelTest {

    private lateinit var viewModel: RestaurantDetailViewModel

    @Before
    fun setUp() {
        val doorDashAPi = DoorDashServiceFactory.retrofit(DoorDashConstants.DOOR_DASH_API_BASE_URL).create(DoorDashAPi::class.java)
        val repo = Repository(doorDashAPi)
        viewModel = RestaurantDetailViewModel(repo)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getStoreRateDistanceText() {
        val json = FileReader("stores_feed.json").content
        val storeFeedResponse = Gson().fromJson(json, StoreFeedResponse::class.java)
        val rateDistanceString = "4.7 • 6934+ ratings • 2.28 mi • $"
        assertEquals(rateDistanceString, viewModel.getStoreRateDistanceText(storeFeedResponse.stores[0]))
    }
}