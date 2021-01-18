package com.jiusong.doordash.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jiusong.doordash.data.Repository
import com.jiusong.doordash.data.model.Store
import java.lang.StringBuilder

/**
 * Created by jiusong.gao on 1/16/21.
 */
class RestaurantDetailModel @ViewModelInject constructor(private val repository: Repository): ViewModel() {

    var store = MutableLiveData<Store>()

    fun getStore(storeId: String) {
        var data = repository.getStore(storeId)
        if (data != null) store.value = data
    }

    fun getStoreRateDistanceText(store: Store): String {
        return store.average_rating.toString() + " • " +
                store.num_ratings+ "+ ratings • " +
                getDistanceString(store.distance_from_consumer) + " mi • " +
                getPriceRangeString(store.price_range)
    }

    private fun getDistanceString(distance: String): String {
        val dis = distance.toFloat()
        return "%.2f".format(dis)
    }

    private fun getPriceRangeString(priceRange: Int): String {
        var price = priceRange
        var sb = StringBuilder()
        while (price > 0) {
            sb.append("$")
            price--
        }
        return sb.toString()
    }
}