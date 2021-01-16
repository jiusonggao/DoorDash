package com.jiusong.doordash.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiusong.doordash.data.Repository
import com.jiusong.doordash.data.model.Store
import kotlinx.coroutines.launch

/**
 * Created by jiusong.gao on 1/15/21.
 */
class StoresViewModel @ViewModelInject constructor(private val repository: Repository): ViewModel() {

    private val lat = "37.422740"
    private val lng = "-122.139956"

    var stores = MutableLiveData<List<Store>>()

    fun fetchStores() {
        viewModelScope.launch {
            val list = repository.getStores(lat, lng)
            stores.value = list
        }
    }
}