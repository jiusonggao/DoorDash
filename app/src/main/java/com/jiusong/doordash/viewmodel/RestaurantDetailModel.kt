package com.jiusong.doordash.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jiusong.doordash.data.Repository
import com.jiusong.doordash.data.model.Store

/**
 * Created by jiusong.gao on 1/16/21.
 */
class RestaurantDetailModel @ViewModelInject constructor(private val repository: Repository): ViewModel() {

    var store = MutableLiveData<Store>()

    fun getStore(storeId: String) {
        var data = repository.getStore(storeId)
        if (data != null) store.value = data
    }
}