package com.jiusong.doordash.data.network

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by jiusong.gao on 1/17/21.
 */
class DoorDashApiHelperTest {

    @Test
    fun getQueryMap() {
        val lat = "37.422740"
        val lng = "-122.139956"
        val offset = 0
        val map = DoorDashApiHelper.getQueryMap(lat, lng, offset)
        assertEquals(lat, map[DoorDashApiHelper.QUERY_KEY_LAT])
        assertEquals(lng, map[DoorDashApiHelper.QUERY_KEY_LNG])
        assertEquals(offset.toString(), map[DoorDashApiHelper.QUERY_KEY_OFFSET])
        assertTrue(map.containsKey(DoorDashApiHelper.QUERY_KEY_LIMIT))
    }
}