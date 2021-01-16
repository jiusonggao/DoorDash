package com.jiusong.doordash.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jiusong.doordash.databinding.ActivityMainBinding
import com.jiusong.doordash.viewmodel.StoresViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: StoresViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this).get(StoresViewModel::class.java)
        setupStoreList()
    }

    private fun setupStoreList() {
        val viewManager = LinearLayoutManager(this)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = StoreListAdapter()
        }
        // Observer stores
        viewModel.stores.observe(this, Observer {
            if (it.isNotEmpty()) {
                (binding.recyclerView.adapter as StoreListAdapter).addStores(it)
            }
        })
        // Fetch stores
        viewModel.fetchStores()
        // Handle scroll to the end of the list
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                // Fetch more stores.
                if (!recyclerView.canScrollVertically(1)) viewModel.fetchStores()
            }
        })
    }
}