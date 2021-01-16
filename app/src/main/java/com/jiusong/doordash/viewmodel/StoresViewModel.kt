package com.jiusong.doordash.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiusong.doordash.data.Repository
import com.jiusong.doordash.data.model.Store
import kotlinx.coroutines.launch

/**
 * Created by jiusong.gao on 1/15/21.
 */
class StoresViewModel: ViewModel() {

    private val lat = "37.422740"
    private val lng = "-122.139956"
    private val repo = Repository()

    var stores = MutableLiveData<List<Store>>()

    fun fetchStores() {
        viewModelScope.launch {
            val list = repo.getStores(lat, lng)
            stores.value = list
        }
    }
}