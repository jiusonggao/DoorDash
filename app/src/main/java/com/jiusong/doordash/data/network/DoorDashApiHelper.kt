package com.jiusong.doordash.data.network

/**
 * Created by jiusong.gao on 1/15/21.
 */
object DoorDashApiHelper {

    private const val limit = 10
    const val QUERY_KEY_LAT = "lat"
    const val QUERY_KEY_LNG = "lng"
    const val QUERY_KEY_OFFSET = "offset"
    const val QUERY_KEY_LIMIT = "limit"

    fun getQueryMap(lat:String, lng:String, offset: Int): Map<String, String> {
        val map = HashMap<String, String>()
        map[QUERY_KEY_LAT] = lat
        map[QUERY_KEY_LNG] = lng
        map[QUERY_KEY_OFFSET] = offset.toString()
        map[QUERY_KEY_LIMIT] = limit.toString()
        return map
    }
}