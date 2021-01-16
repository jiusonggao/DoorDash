package com.jiusong.doordash.data.network

import com.jiusong.doordash.data.model.StoreFeedResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by jiusong.gao on 1/15/21.
 */
interface DoorDashAPi {

    @GET("v1/store_feed/")
    suspend fun getStores(@QueryMap queryMap: Map<String, String>): StoreFeedResponse
}