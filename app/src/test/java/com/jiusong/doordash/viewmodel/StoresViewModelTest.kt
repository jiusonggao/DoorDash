package com.jiusong.doordash.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jiusong.doordash.data.Repository
import com.jiusong.doordash.data.network.DoorDashAPi
import com.jiusong.doordash.data.network.DoorDashServiceFactory
import com.jiusong.doordash.util.DoorDashConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * Created by jiusong.gao on 1/17/21.
 */
@ExperimentalCoroutinesApi
class StoresViewModelTest {
    @Rule @JvmField val rule = InstantTaskExecutorRule()
    private lateinit var viewModel: StoresViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        val doorDashAPi = DoorDashServiceFactory.retrofit(DoorDashConstants.DOOR_DASH_API_BASE_URL).create(DoorDashAPi::class.java)
        val repository = Repository(doorDashAPi)
        viewModel = StoresViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun loadStores() {
        viewModel.stores.observeForever( Observer {
          assertTrue(it.isNotEmpty())
        })
        viewModel.loadStores()
    }

    @Test
    fun loadMoreStores() {
        viewModel.stores.observeForever(Observer {
            assertTrue(it.isNotEmpty())
        })
        viewModel.loadMoreStores()
    }
}