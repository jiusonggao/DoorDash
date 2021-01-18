package com.jiusong.doordash.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jiusong.doordash.data.network.Status
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
        observeStores()
        // Handle scroll to the end of the list
        handleListScrolling()
        // Fetch stores
        viewModel.loadStores()
    }

    private fun observeStores() {
        viewModel.stores.observe(this, Observer {
            when(it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.msg, Toast.LENGTH_LONG)
                        .show()
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { list -> storesAdapter.addStores(list) }
                }
            }
        })
    }

    private fun handleListScrolling() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                // Fetch more stores.
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadMoreStores()
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onClick(storeId: String) {
        val intent = Intent(this, RestaurantDetailActivity::class.java)
        intent.putExtra(DoorDashConstants.STORE_ID, storeId)
        startActivity(intent)
    }
}