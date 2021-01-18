package com.jiusong.doordash.data

import com.jiusong.doordash.data.model.Store
import com.jiusong.doordash.data.network.DoorDashAPi
import com.jiusong.doordash.data.network.DoorDashApiHelper
import com.jiusong.doordash.data.network.Resource
import com.jiusong.doordash.data.network.ResponseHandler
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by jiusong.gao on 1/15/21.
 */
@Singleton
class Repository@Inject constructor(private val doorDashService: DoorDashAPi) {
    private var offset = 0
    @Inject lateinit var resourceHandler: ResponseHandler
    var stores= mutableListOf<Store>()

    suspend fun getStores(lat: String, lng: String): Resource<List<Store>> {
        return try {
            val map = DoorDashApiHelper.getQueryMap(lat, lng, offset)
            val storeFeed = doorDashService.getStores(map)
            offset = storeFeed.next_offset
            stores.addAll(storeFeed.stores)
            resourceHandler.handleSuccess(storeFeed.stores)
        } catch (e: Exception) {
            e.printStackTrace()
            resourceHandler.handleException(e)
        }
    }

    fun getStore(storeId: String) : Store? {
        for (store: Store in stores) {
            if (storeId == store.id) return store
        }
        return null
    }
}