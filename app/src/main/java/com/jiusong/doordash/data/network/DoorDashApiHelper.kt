package com.jiusong.doordash.data.network

/**
 * Created by jiusong.gao on 1/15/21.
 */
object DoorDashApiHelper {

    private const val limit = 10

    fun getQueryMap(lat:String, lng:String, offset: Int): Map<String, String> {
        val map = HashMap<String, String>()
        map["lat"] = lat
        map["lng"] = lng
        map["offset"] = offset.toString()
        map["limit"] = limit.toString()
        return map
    }
}