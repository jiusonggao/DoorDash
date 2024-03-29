package com.jiusong.doordash.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jiusong.doordash.data.model.Item
import com.jiusong.doordash.data.model.Menu
import com.jiusong.doordash.databinding.ActivityRestaurantDetailBinding
import com.jiusong.doordash.ui.recycerview.MenusAdapter
import com.jiusong.doordash.util.DoorDashConstants
import com.jiusong.doordash.viewmodel.RestaurantDetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by jiusong.gao on 1/16/21.
 */
@AndroidEntryPoint
class RestaurantDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestaurantDetailBinding
    private val viewModel: RestaurantDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolBar()
        setupUI()
        viewModel.getStore(intent.getStringExtra(DoorDashConstants.STORE_ID).orEmpty())
    }

    private fun setupToolBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupUI() {
        viewModel.store.observe(this, Observer {
            Picasso.get().load(it.cover_img_url).into(binding.coverImage)
            binding.storeName.text = it.name
            binding.storeDescription.text = it.description
            binding.storeRateDis.text = viewModel.getStoreRateDistanceText(it)
            supportActionBar?.title = it.name
            setupPopularMenus(it.menus)
        })
    }

    private fun setupPopularMenus(menus: List<Menu>) {
        var items = mutableListOf<Item>()
        for (menu: Menu in menus) {
            items.addAll(menu.popular_items)
        }
        var viewManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.menus.apply {
            layoutManager = viewManager
            setHasFixedSize(true)
            adapter = MenusAdapter(items)
        }
    }
}