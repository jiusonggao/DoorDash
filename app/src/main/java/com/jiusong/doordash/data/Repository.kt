package com.jiusong.doordash.data

import com.jiusong.doordash.data.model.Store
import com.jiusong.doordash.data.network.DoorDashApiHelper
import com.jiusong.doordash.data.network.DoorDashServiceFactory

/**
 * Created by jiusong.gao on 1/15/21.
 */
class Repository {
    private var offset = 0
    private val service = DoorDashServiceFactory.doorDashService

    suspend fun getStores(lat: String, lng: String): List<Store> {
        val map = DoorDashApiHelper.getQueryMap(lat, lng, offset)
        val storeFeed = service.getStores(map)
        offset = storeFeed.next_offset
        return storeFeed.stores
    }
}