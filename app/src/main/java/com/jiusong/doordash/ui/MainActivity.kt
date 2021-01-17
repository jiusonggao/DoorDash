package com.jiusong.doordash.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jiusong.doordash.databinding.ActivityMainBinding
import com.jiusong.doordash.ui.recycerview.StoreItemClickListener
import com.jiusong.doordash.ui.recycerview.StoreListAdapter
import com.jiusong.doordash.util.DoorDashConstants
import com.jiusong.doordash.viewmodel.StoresViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), StoreItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: StoresViewModel by viewModels()
    @Inject lateinit var storesAdapter: StoreListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupStoreList()
    }

    private fun setupStoreList() {
        val viewManager = LinearLayoutManager(this)
        storesAdapter.setListener(this)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = storesAdapter
        }
        // Observer stores
        viewModel.stores.observe(this, Observer {
            if (it.isNotEmpty()) {
                storesAdapter.addStores(it)
            }
        })
        // Fetch stores
        viewModel.loadStores()
        // Handle scroll to the end of the list
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                // Fetch more stores.
                if (!recyclerView.canScrollVertically(1)) viewModel.loadMoreStores()
            }
        })
    }

    override fun onClick(storeId: String) {
        val intent = Intent(this, RestaurantDetailActivity::class.java)
        intent.putExtra(DoorDashConstants.STORE_ID, storeId)
        startActivity(intent)
    }
}