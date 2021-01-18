package com.jiusong.doordash.data

import com.jiusong.doordash.data.network.DoorDashAPi
import com.jiusong.doordash.data.network.DoorDashServiceFactory
import com.jiusong.doordash.util.DoorDashConstants
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by jiusong.gao on 1/18/21.
 */
class RepositoryTest {

    private lateinit var repository: Repository
    private val lat = "37.422740"
    private val lng = "-122.139956"

    @Before
    fun setUp() {
        val doorDashAPi = DoorDashServiceFactory.retrofit(DoorDashConstants.DOOR_DASH_API_BASE_URL).create(DoorDashAPi::class.java)
        repository = Repository(doorDashAPi)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getStores() {
        runBlocking {
            val stores = repository.getStores(lat, lng)
            assertNotNull(stores)
        }
    }

    @Test
    fun getStore() {
        runBlocking {
            val stores = repository.getStores(lat, lng)
            val store = stores[stores.size/2]
            assertEquals(store, repository.getStore(store.id))
        }
    }
}