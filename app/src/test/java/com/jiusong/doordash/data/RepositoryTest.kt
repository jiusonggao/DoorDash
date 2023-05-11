package com.jiusong.doordash.data

import com.jiusong.doordash.data.network.DoorDashAPi
import com.jiusong.doordash.data.network.DoorDashServiceFactory
import com.jiusong.doordash.data.network.ResponseHandler
import com.jiusong.doordash.data.network.Status
import com.jiusong.doordash.util.DoorDashConstants
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Ignore

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
        repository.resourceHandler = ResponseHandler()
    }

    @After
    fun tearDown() {
    }

    @Test
    @Ignore
    fun testGetStoresSuccess() {
        runBlocking {
            val resource = repository.getStores(lat, lng)
            assertEquals(Status.SUCCESS, resource.status)
        }
    }

    @Test
    fun testGetStoresFail() {
        runBlocking {
            val resource = repository.getStores(lat, "null")
            assertEquals(Status.ERROR, resource.status)
        }
    }

    @Test
    @Ignore
    fun getStore() {
        runBlocking {
            val resources = repository.getStores(lat, lng)
            val stores = resources.data
            assertNotNull(stores)
            if (stores != null) {
                val store = stores[stores.size/2]
                assertEquals(store, repository.getStore(store.id))
            }
        }
    }
}