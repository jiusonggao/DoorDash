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
import com.jiusong.doordash.viewmodel.RestaurantDetailModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by jiusong.gao on 1/16/21.
 */
@AndroidEntryPoint
class RestaurantDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestaurantDetailBinding
    private val viewModel: RestaurantDetailModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        viewModel.getStore(intent.getStringExtra(DoorDashConstants.STORE_ID))
    }

    private fun setupUI() {
        viewModel.store.observe(this, Observer {
            Picasso.get().load(it.cover_img_url).into(binding.coverImage)
            binding.storeName.text = it.name
            binding.storeDescription.text = it.description
            binding.storeRateDis.text = it.average_rating.toString()
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